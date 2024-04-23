package com.ecommercwebsite.aioscrm.ui.leads.model

data class LeadsModel(
    val id: String,
    val name: String,
    val company: String,
    val email: String,
    val phonenumber: String,
    val leadValue: String,
    val status: String,
    val source: String,
    val lastcontact: String,
    val dateadded: String
)
