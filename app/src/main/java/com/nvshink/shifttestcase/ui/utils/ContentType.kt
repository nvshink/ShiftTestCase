package com.nvshink.shifttestcase.ui.utils

/**
 * Determines how the content will be displayed
 */
enum class ContentType {
    /**
     * Defines the display of content in single mode, i.e. without the possibility of simultaneous work with the list and its detailed elements.
     */
    LIST_ONLY,

    /**
     * Defines the display of content in a collaborative mode, i.e. both a list and details about the selected item are displayed.
     */
    LIST_AND_DETAIL
}