package com.example.helloandroid.timerecording.view

import android.content.Context
import android.widget.ArrayAdapter
import com.example.helloandroid.R

class TitlesArrayAdapter(context: Context, titles: List<String>) :
    ArrayAdapter<String>(context, R.layout.item_list_popup_window, titles)