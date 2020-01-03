package com.example.helloandroid.finances.view

import android.app.Activity
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.HelloDatePickerDialog
import com.example.helloandroid.R
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.AusgabenContainer
import com.example.helloandroid.view.BigDecimalConverter
import com.example.helloandroid.view.LocalDateConverter
import com.example.helloandroid.view.SortDirection
import com.google.android.material.textfield.TextInputEditText
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.math.BigDecimal
import java.util.*

class AusgabeRecyclerViewAdapter(private val parentActivity: Activity, private val ausgabenContainer: AusgabenContainer,
                                 private val deleteAusgabe: (Ausgabe) -> Unit,
                                 private val editAusgabe: (Ausgabe) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var startDateAusgabe: LocalDate
    private var endDateAusgabe: LocalDate
    private var ausgabenForDateRange: MutableList<Ausgabe>
    private var currentSortState: AusgabenSortStates

    init {
        startDateAusgabe = ausgabenContainer.findEarliestAusgabe()
        endDateAusgabe = LocalDate.now()
        ausgabenForDateRange = ausgabenContainer.findAusgabenForDateRange(startDateAusgabe, endDateAusgabe)
        currentSortState = AusgabenSortStates.DATUM_AUFSTEIGEND
        sortAusgaben()
    }


    companion object {
        const val VIEW_TYPE_HEADER: Int = 0
        const val VIEW_TYPE_ITEM: Int = 1
        const val MENU_ITEM_EDIT_AUSGABE = 1
        const val MENU_ITEM_DELETE_AUSGABE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            createHeaderViewHolder(parent)
        } else {
            ItemViewHolder(inflate(R.layout.item_ausgabe, parent))
        }
    }

    private fun createHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        val headerViewHolder = HeaderViewHolder(inflate(R.layout.ausgabe_header_row, parent))
        headerViewHolder.btnStartDateAusgaben.setOnClickListener { openDatePickerDialogForStartDate() }
        headerViewHolder.btnEndDateAusgaben.setOnClickListener { openDatePickerDialogForEndDate() }
        headerViewHolder.setDatumSortIconAscending()
        headerViewHolder.layoutDatum.setOnClickListener {
            when (currentSortState) {
                AusgabenSortStates.DATUM_ABSTEIGEND -> {
                    currentSortState = AusgabenSortStates.DATUM_AUFSTEIGEND
                    headerViewHolder.setDatumSortIconAscending()
                }
                AusgabenSortStates.DATUM_AUFSTEIGEND -> {
                    currentSortState = AusgabenSortStates.DATUM_ABSTEIGEND
                    headerViewHolder.setDatumSortIconDescending()
                }
                else -> {
                    currentSortState = AusgabenSortStates.DATUM_AUFSTEIGEND
                    headerViewHolder.setDatumSortIconAscending()
                    headerViewHolder.setWertSortIconNeutral()
                }
            }
            sortAusgaben()
            notifyDataSetChanged()
        }
        headerViewHolder.layoutWert.setOnClickListener {
            when (currentSortState) {
                AusgabenSortStates.WERT_ABSTEIGEND -> {
                    currentSortState = AusgabenSortStates.WERT_AUFSTEIGEND
                    headerViewHolder.setWertSortIconAscending()
                }
                AusgabenSortStates.WERT_AUFSTEIGEND -> {
                    currentSortState = AusgabenSortStates.WERT_ABSTEIGEND
                    headerViewHolder.setWertSortIconDescending()
                }
                else -> {
                    currentSortState = AusgabenSortStates.WERT_AUFSTEIGEND
                    headerViewHolder.setWertSortIconAscending()
                    headerViewHolder.setDatumSortIconNeutral()
                }
            }
            sortAusgaben()
            notifyDataSetChanged()
        }

        return headerViewHolder
    }



    private fun inflate(layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }


    private fun sortAusgaben() {
        when (currentSortState) {
            AusgabenSortStates.DATUM_AUFSTEIGEND -> sortByDatumAufsteigend()
            AusgabenSortStates.DATUM_ABSTEIGEND -> sortByDatumAbsteigend()
            AusgabenSortStates.WERT_AUFSTEIGEND -> sortByWertAufsteigend()
            AusgabenSortStates.WERT_ABSTEIGEND -> sortByWertAbsteigend()
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


    private fun openDatePickerDialogForStartDate() {
        val dateSetListener: (LocalDate) -> Unit = {
            startDateAusgabe = it
            ausgabenForDateRange = ausgabenContainer.findAusgabenForDateRange(startDateAusgabe, endDateAusgabe)
            notifyDataSetChanged()
        }
        HelloDatePickerDialog(parentActivity, dateSetListener, endDateAusgabe).show()
    }

    private fun openDatePickerDialogForEndDate() {
        val dateSetListener: (LocalDate) -> Unit = {
            endDateAusgabe = it
            ausgabenForDateRange = ausgabenContainer.findAusgabenForDateRange(startDateAusgabe, endDateAusgabe)
            notifyDataSetChanged()
        }
        HelloDatePickerDialog(parentActivity, dateSetListener, endDateAusgabe).show()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.btnStartDateAusgaben.setText(LocalDateConverter.dateToString(startDateAusgabe))
            holder.btnEndDateAusgaben.setText(LocalDateConverter.dateToString(endDateAusgabe))
        } else if (holder is ItemViewHolder) {
            val currentAusgabe = ausgabenForDateRange[position - 1]
            holder.datumTextView.text = createDateTimeForAnzeige(currentAusgabe.datum)
            holder.wertTextView.text = BigDecimalConverter.bigDecimalToString(currentAusgabe.wert)
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
        return ausgabenContainer.findAusgabenForDateRange(startDate, endDate).stream()//
            .map(Ausgabe::wert)//
            .reduce(BigDecimal::add)//
            .orElse(BigDecimal.ZERO)
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
        val datumTextView: TextView = itemView.findViewById(R.id.ausgabe_item_datum)
        val wertTextView: TextView = itemView.findViewById(R.id.ausgabe_item_wert)
        val beschreibungTextView: TextView = itemView.findViewById(R.id.ausgabe_item_beschreibung)
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
        val btnStartDateAusgaben: TextInputEditText = itemView.findViewById(R.id.btn_ausgabe_date_range_start)
        val btnEndDateAusgaben: TextInputEditText = itemView.findViewById(R.id.btn_ausgabe_date_range_end)
        val layoutDatum: ViewGroup = itemView.findViewById(R.id.ausgabe_header_row_datum)
        private val sortIconDatum: ImageView = itemView.findViewById(R.id.image_view_ausgabe_header_row_datum)
        val layoutWert: ViewGroup = itemView.findViewById(R.id.ausgabe_header_rowdatum_wert)
        private val sortIconWert: ImageView = itemView.findViewById(R.id.image_view_ausgabe_header_rowdatum_wert)

        fun setDatumSortIconAscending() {
            setAscendingArrow(sortIconDatum)
        }

        fun setDatumSortIconDescending() {
            setDescendingArrow(sortIconDatum)
        }

        fun setDatumSortIconNeutral() {
            setNeutralArrow(sortIconDatum)
        }

        fun setWertSortIconAscending() {
            setAscendingArrow(sortIconWert)
        }

        fun setWertSortIconDescending() {
            setDescendingArrow(sortIconWert)
        }

        fun setWertSortIconNeutral() {
            setNeutralArrow(sortIconWert)
        }

        private fun setAscendingArrow(imageView: ImageView) {
            imageView.setImageResource(R.drawable.ic_arrow_up_down_sort_ascending_24dp)
        }

        private fun setDescendingArrow(imageView: ImageView) {
            imageView.setImageResource(R.drawable.ic_arrow_up_down_sort_descending_24dp)
        }

        private fun setNeutralArrow(imageView: ImageView) {
            imageView.setImageResource(R.drawable.ic_arrow_up_down_grey_24dp)
        }

    }


}