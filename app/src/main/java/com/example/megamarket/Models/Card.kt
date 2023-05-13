package com.example.megamarket.Models

data class Card(val cardID: String, val name: String, val number: String, val cvv: String, val date: String) {
    constructor() : this("", "", "", "", "")
}


