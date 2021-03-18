package com.mobilife.employyim.contract

interface SplashContract {
    interface View {
        fun finishView()
    }

    interface Presenter {
        val view: View

        // Model updates
        fun onViewCreated()
    }
}
