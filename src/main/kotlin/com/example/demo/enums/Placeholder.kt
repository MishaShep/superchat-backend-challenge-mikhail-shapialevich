package com.example.demo.enums

enum class Placeholder(val value: String) {
    CONTACT_NAME("@contact"),
    FULL_NAME("@fullname"),
    BITCOIN("@bitcoin");

    companion object {
        infix fun from(value: String): Placeholder? = Placeholder.values().firstOrNull { it.value == value }
    }
}