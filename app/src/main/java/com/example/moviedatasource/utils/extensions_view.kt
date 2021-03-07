package com.example.moviedatasource.utils

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

fun View.animateShowUp() {
    if (this.visibility != View.VISIBLE) {
        this.animate()
            .translationY(1f)
            .alpha(1f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(500)
            .withStartAction { this.visibility = View.VISIBLE }
    }
}

fun View.animateShowDown() {
    if (this.visibility != View.VISIBLE) {
        this.animate()
            .translationY(0f)
            .alpha(1f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(500)
            .withStartAction { this.visibility = View.VISIBLE }
    }
}

fun View.animateHideUp() {
    if (this.visibility == View.VISIBLE) {
        this.animate()
            .translationY(2f)
            .alpha(0f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(500)
            .withEndAction { this.visibility = View.GONE }
    }
}

fun View.animateHideDown() {
    if (this.visibility == View.VISIBLE) {
        this.animate()
            .translationY(0f)
            .alpha(0f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(500)
            .withEndAction { this.visibility = View.GONE }
    }
}