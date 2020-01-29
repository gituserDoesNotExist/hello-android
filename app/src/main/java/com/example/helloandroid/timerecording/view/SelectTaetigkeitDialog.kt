package com.example.helloandroid.timerecording.view

import com.example.helloandroid.R
import com.example.helloandroid.timerecording.config.Taetigkeit

class SelectTaetigkeitDialog : SelectFilterDialog<Taetigkeit>() {


    override fun createRecyclerViewAdapter(
        config: CalendarConfiguration): SelectFiltersRecyclerViewAdapter<Taetigkeit> {
        val filterItems = extractFilterValuesFromConfig(config)
        return object : SelectFiltersRecyclerViewAdapter<Taetigkeit>(filterItems) {
            override fun anzeigeValue(currentItem: CheckableFilterItem<Taetigkeit>): String {
                return currentItem.item.bezeichnung
            }

            override fun onCheckboxSelectedListener(): (Boolean, Taetigkeit) -> Unit = { checked, item ->
                if (checked) filterViewModel.addSelectedItemTaetigkeit(item)
                else filterViewModel.removeSelectedItemTaetigkeit(item)
            }
        }
    }


    override fun dialogTitle(): String {
        return resources.getString(R.string.taetigkeit)
    }

    private fun extractFilterValuesFromConfig(config: CalendarConfiguration): List<CheckableFilterItem<Taetigkeit>> {
        val allCategories = config.teatigkeiten
        val selectedTaetigkeiten = filterViewModel.suchkriterien.taetigkeitenToFilter
        return allCategories.map {
            val alreadyPresent = selectedTaetigkeiten.contains(it)
            if (alreadyPresent) CheckableFilterItem(true, it)
            else CheckableFilterItem(item = it)
        }
    }


    override fun saveSelectedFilterValues() {
        filterViewModel.saveSelectedTaetigkeitItems()
    }

    override fun initSelectedFilterValues() {
        filterViewModel.suchkriterien.taetigkeitenToFilter.forEach { filterViewModel.addSelectedItemTaetigkeit(it) }
    }


}