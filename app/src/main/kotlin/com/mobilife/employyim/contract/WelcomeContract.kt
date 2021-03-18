package com.mobilife.employyim.contract

interface WelcomeContract {
    interface View {
        fun finishView()
    }

    interface Presenter {
        val view: View
        fun onClickStart()
    }
}
