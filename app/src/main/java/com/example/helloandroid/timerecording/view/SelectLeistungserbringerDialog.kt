package com.example.helloandroid.timerecording.view

import com.example.helloandroid.timerecording.config.Person

class SelectLeistungserbringerDialog : SelectFilterDialog<Person>() {

    override fun createRecyclerViewAdapter(config: CalendarConfiguration): SelectFiltersRecyclerViewAdapter<Person> {
        val filterItems = extractFilterValuesFromConfig(config)
        return object : SelectFiltersRecyclerViewAdapter<Person>(filterItems) {
            override fun anzeigeValue(currentItem: CheckableFilterItem<Person>): String {
                return currentItem.item.name
            }

            override fun onCheckboxSelectedListener(): (Boolean, Person) -> Unit = { checked, item ->
                if (checked) filterViewModel.addSelectedItemLeistungserbringer(item)
                else filterViewModel.removeSelectedItemLeistungserbringer(item)
            }
        }
    }

    private fun extractFilterValuesFromConfig(config: CalendarConfiguration): List<CheckableFilterItem<Person>> {
        val allParticipants = config.teilnehmer
        val selectedLeistungserbringer = filterViewModel.suchkriterien.leistungserbringerToFilter
        return allParticipants.map {
            val alreadyPresent = selectedLeistungserbringer.contains(it)
            if (alreadyPresent) CheckableFilterItem(true, it) else CheckableFilterItem(item = it)
        }
    }


    override fun saveSelectedFilterValues() {
        filterViewModel.saveSelectedLeistungserbringerItems()
    }

    override fun initSelectedFilterValues() {
        filterViewModel.suchkriterien.leistungserbringerToFilter.forEach {
            filterViewModel.addSelectedItemLeistungserbringer(it)
        }
    }

}