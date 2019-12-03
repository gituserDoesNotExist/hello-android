package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CalendarViews {

    @SerializedName("default_view")
    @Expose
    var defaultView: String? = null
    @SerializedName("show_day_view")
    @Expose
    var showDayView: Boolean? = null
    @SerializedName("show_week_view")
    @Expose
    var showWeekView: Boolean? = null
    @SerializedName("show_multiday_view")
    @Expose
    var showMultidayView: Boolean? = null
    @SerializedName("show_multiweek_view")
    @Expose
    var showMultiweekView: Boolean? = null
    @SerializedName("show_month_view")
    @Expose
    var showMonthView: Boolean? = null
    @SerializedName("show_agenda_view")
    @Expose
    var showAgendaView: Boolean? = null
    @SerializedName("show_list_view")
    @Expose
    var showListView: Boolean? = null
    @SerializedName("show_scheduler_view")
    @Expose
    var showSchedulerView: Boolean? = null
    @SerializedName("show_year_view")
    @Expose
    var showYearView: Boolean? = null
    @SerializedName("week_hide_weekend")
    @Expose
    var weekHideWeekend: Boolean? = null
    @SerializedName("multiweek_number_of_weeks")
    @Expose
    var multiweekNumberOfWeeks: Int? = null
    @SerializedName("multiday_number_of_days")
    @Expose
    var multidayNumberOfDays: Int? = null
    @SerializedName("agenda_date_range")
    @Expose
    var agendaDateRange: String? = null
    @SerializedName("agenda_show_details")
    @Expose
    var agendaShowDetails: Boolean? = null
    @SerializedName("list_date_range")
    @Expose
    var listDateRange: String? = null
    @SerializedName("list_group_by")
    @Expose
    var listGroupBy: String? = null
    @SerializedName("list_show_details")
    @Expose
    var listShowDetails: Boolean? = null
    @SerializedName("year_number_of_months")
    @Expose
    var yearNumberOfMonths: Int? = null

}

