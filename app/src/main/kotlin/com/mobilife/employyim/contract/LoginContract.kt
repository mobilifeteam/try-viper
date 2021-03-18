package com.mobilife.employyim.contract

interface LoginContract {
    interface View {
        fun finishView()
    }

    interface Presenter {
        val view: View
        fun onViewCreated()
    }
}
