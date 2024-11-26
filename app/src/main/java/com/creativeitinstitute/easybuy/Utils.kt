package com.creativeitinstitute.easybuy

import android.widget.EditText

fun EditText.isEmpty() : Boolean{

    if (this.text.toString().isEmpty()){
        this.error = "This place need to be fill up"
        return true
    }else{
        return false
    }
}