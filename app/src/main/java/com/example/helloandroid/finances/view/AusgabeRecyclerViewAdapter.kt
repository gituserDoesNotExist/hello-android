package com.example.helloandroid.finances.view

import android.app.Activity
import android.app.DatePickerDialog
import android.view.*
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R.id
import com.example.helloandroid.R.layout
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.view.BigDecimalConverter
import com.example.helloandroid.view.HelloSpinnerAdapter
import com.example.helloandroid.view.LocalDateConverter
import com.example.helloandroid.view.SortDirection
import com.google.android.material.textfield.TextInputEditText
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.math.BigDecimal
import java.util.*
import java.util.stream.Collectors

class AusgabeRecyclerViewAdapter(private val parentActivity: Activity, private val allAusgaben: List<Ausgabe>,
                                 private val deleteAusgabe: (Ausgabe) -> Unit,
                                 private val editAusgabe: (Ausgabe) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var startDateAusgabe: LocalDate
    private var endDateAusgabe: LocalDate
    private var ausgabenForDateRange: MutableList<Ausgabe>

    init {
        startDateAusgabe = findEarliestAusgabe(allAusgaben)
        endDateAusgabe = LocalDate.now()
        ausgabenForDateRange = findAusgabenForDateRange(startDateAusgabe, endDateAusgabe)
    }

    private fun findEarliestAusgabe(ausgaben: List<Ausgabe>): LocalDate {
        return ausgaben.stream()//
            .map(Ausgabe::datum)//
            .min { o1, o2 -> o1.compareTo(o2) }//
            .orElse(LocalDateTime.now()).toLocalDate()
    }

    companion object {
        const val VIEW_TYPE_HEADER: Int = 0
        const val VIEW_TYPE_ITEM: Int = 1
        private val DROPDOWN_ENTRIES =
            listOf("Datum aufsteigend", "Datum absteigend", "Wert aufsteigend", "Wert absteigend", "Beschreibung")
        const val MENU_ITEM_EDIT_AUSGABE = 1
        const val MENU_ITEM_DELETE_AUSGABE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            createHeaderViewHolder(parent)
        } else {
            ItemViewHolder(inflate(layout.ausgabe_item, parent))
        }
    }

    private fun createHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        val headerView = inflate(layout.ausgabe_header_row, parent)
        headerView.findViewById<Spinner>(id.spinner_ausgabe).also {
            it.onItemSelectedListener = onItemSelectListener()
            it.adapter = HelloSpinnerAdapter(parent.context, DROPDOWN_ENTRIES)
        }
        val headerViewHolder = HeaderViewHolder(headerView)
        headerViewHolder.btnStartDateAusgaben.setOnClickListener { openDatePickerDialogForStartDate() }
        headerViewHolder.btnEndDateAusgaben.setOnClickListener { openDatePickerDialogForEndDate() }
        return headerViewHolder
    }

    private fun inflate(layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    private fun onItemSelectListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sortAusgaben(position)
                notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun sortAusgaben(positionOfSortCriteria: Int) {
        when (positionOfSortCriteria) {
            0 -> sortByDatumAufsteigend()
            1 -> sortByDatumAbsteigend()
            2 -> sortByWertAufsteigend()
            3 -> sortByWertAbsteigend()
            4 -> sortAusgabenByBeschreibung()
        }
    }

    private fun sortByDatumAufsteigend() {
        ausgabenForDateRange.sortWith(Comparator { o1, o2 -> sortAusgabenByDate(SortDirection.ASCENDING, o1, o2) })
    }

    private fun sortByDatumAbsteigend() {
        ausgabenForDateRange.sortWith(Comparator { o1, o2 -> sortAusgabenByDate(SortDirection.DESCENDING, o1, o2) })
    }

    private fun sortAusgabenByDate(sortDirection: SortDirection, ausgabe: Ausgabe, otherAusgabe: Ausgabe): Int {
        return if (SortDirection.ASCENDING == sortDirection) {
            ausgabe.datum.compareTo(otherAusgabe.datum)
        } else {
            otherAusgabe.datum.compareTo(ausgabe.datum)
        }
    }

    private fun sortByWertAufsteigend() {
        ausgabenForDateRange.sortWith(Comparator { o1, o2 -> sortAusgabenByWert(SortDirection.ASCENDING, o1, o2) })
    }

    private fun sortByWertAbsteigend() {
        ausgabenForDateRange.sortWith(Comparator { o1, o2 -> sortAusgabenByWert(SortDirection.DESCENDING, o1, o2) })
    }

    private fun sortAusgabenByWert(sortDirection: SortDirection, ausgabe: Ausgabe, otherAusgabe: Ausgabe): Int {
        return if (SortDirection.ASCENDING == sortDirection) {
            ausgabe.wert.compareTo(otherAusgabe.wert)
        } else {
            otherAusgabe.wert.compareTo(ausgabe.wert)
        }
    }

    private fun sortAusgabenByBeschreibung() {
        ausgabenForDateRange.sortWith(Comparator { o1, o2 -> o1.beschreibung.compareTo(o2.beschreibung) })
    }


    private fun openDatePickerDialogForStartDate() {
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            startDateAusgabe = LocalDate.of(year, month, dayOfMonth)
            ausgabenForDateRange = findAusgabenForDateRange(startDateAusgabe, endDateAusgabe)
            notifyDataSetChanged()
        }
        openDatePickerDialogForDate(startDateAusgabe, onDateSetListener)
    }

    private fun openDatePickerDialogForEndDate() {
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            endDateAusgabe = LocalDate.of(year, month, dayOfMonth)
            ausgabenForDateRange = findAusgabenForDateRange(startDateAusgabe, endDateAusgabe)
            notifyDataSetChanged()
        }
        openDatePickerDialogForDate(endDateAusgabe, onDateSetListener)
    }

    private fun openDatePickerDialogForDate(date: LocalDate, listener: DatePickerDialog.OnDateSetListener) {
        DatePickerDialog(parentActivity, listener, date.year, date.monthValue, date.dayOfMonth).show()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.btnStartDateAusgaben.setText(LocalDateConverter.dateToString(startDateAusgabe))
            holder.btnEndDateAusgaben.setText(LocalDateConverter.dateToString(endDateAusgabe))
            val ausgabenForDateRange = calculateAusgabenForDateRange(startDateAusgabe, endDateAusgabe)
            holder.textViewAusgabenForDateRange.text = BigDecimalConverter.bigDecimalToString(ausgabenForDateRange)
        } else if (holder is ItemViewHolder) {
            val currentAusgabe = ausgabenForDateRange[position - 1]
            holder.datumTextView.text = createDateTimeForAnzeige(currentAusgabe.datum)
            holder.wertTextView.text = currentAusgabe.wert.toString()
            holder.beschreibungTextView.text = currentAusgabe.beschreibung
            holder.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
                when (it.itemId) {
                    MENU_ITEM_EDIT_AUSGABE -> editAusgabe(currentAusgabe)
                    MENU_ITEM_DELETE_AUSGABE -> deleteAusgabe(currentAusgabe)
                }
                true
            })
        }
    }

    private fun calculateAusgabenForDateRange(startDate: LocalDate, endDate: LocalDate): BigDecimal {
        return findAusgabenForDateRange(startDate, endDate).stream()//
            .map(Ausgabe::wert)//
            .reduce(BigDecimal::add)//
            .orElse(BigDecimal.ZERO)
    }

    private fun findAusgabenForDateRange(startDate: LocalDate, endDate: LocalDate): MutableList<Ausgabe> {
        return allAusgaben.stream()//
            .filter { t -> !t.datum.toLocalDate().isBefore(startDate) && !t.datum.toLocalDate().isAfter(endDate) }//
            .collect(Collectors.toList())
    }


    private fun createDateTimeForAnzeige(datum: LocalDateTime): String {
        return datum.format(DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm"))
    }

    override fun getItemCount(): Int {
        return ausgabenForDateRange.size.plus(1)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == VIEW_TYPE_HEADER) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {

        val datumTextView: TextView = itemView.findViewById(id.ausgabe_item_datum)
        val wertTextView: TextView = itemView.findViewById(id.ausgabe_item_wert)
        val beschreibungTextView: TextView = itemView.findViewById(id.ausgabe_item_beschreibung)
        private lateinit var itemClickListener: MenuItem.OnMenuItemClickListener

        init {
            itemView.setOnCreateContextMenuListener(this) //REGISTER ONCREATE MENU LISTENER
        }

        fun setOnMenuItemClickListener(listener: MenuItem.OnMenuItemClickListener) {
            this.itemClickListener = listener
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, MENU_ITEM_EDIT_AUSGABE, 1, "Edit")?.setOnMenuItemClickListener(itemClickListener)
            menu?.add(Menu.NONE, MENU_ITEM_DELETE_AUSGABE, 2, "Delete")?.setOnMenuItemClickListener(itemClickListener)
        }
    }

    class HeaderViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnStartDateAusgaben: TextInputEditText = itemView.findViewById(id.btn_ausgabe_date_range_start)
        val btnEndDateAusgaben: TextInputEditText = itemView.findViewById(id.btn_ausgabe_date_range_end)
        val textViewAusgabenForDateRange: TextView = itemView.findViewById(id.txt_gesamtausgaben_for_date_range)
    }


}