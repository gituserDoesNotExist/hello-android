package com.example.helloandroid.timerecording.view

import android.content.Context
import android.widget.ArrayAdapter

class TitlesArrayAdapter(context: Context, titles: List<String>) :
    ArrayAdapter<String>(context, android.R.layout.select_dialog_item, titles) 