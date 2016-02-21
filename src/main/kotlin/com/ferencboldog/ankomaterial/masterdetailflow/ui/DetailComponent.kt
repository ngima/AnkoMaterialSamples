package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import android.support.design.widget.CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.GravityCompat.*
import android.support.v4.view.ViewCompat
import android.support.v4.view.WindowInsetsCompat
import android.view.Gravity
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.FrameLayout
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.R.style.ThemeOverlay_AppCompat_Dark_ActionBar
import com.ferencboldog.ankomaterial.R.style.ThemeOverlay_AppCompat_Light
import com.ferencboldog.ankomaterial.extensions.*
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.masterdetailflow.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.support.v4.nestedScrollView

class DetailComponent: AnkoComponent<DetailActivity>, AnkoLogger {

    companion object {
        val TOOLBAR_LAYOUT = generateViewId()
        val TOOLBAR = generateViewId()
        val DETAIL_CONTAINER = generateViewId()
        val FAB = generateViewId()
    }

    fun collapseModeParams(): android.support.design.widget.CollapsingToolbarLayout.LayoutParams.() -> Unit = {collapseMode = COLLAPSE_MODE_PIN}

    override fun createView(ui: AnkoContext<DetailActivity>): View = with(ui) {
        coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout(ThemeOverlay_AppCompat_Dark_ActionBar) {
                fitsSystemWindows = true

                collapsingToolbarLayout {
                    id = TOOLBAR_LAYOUT
                    fitsSystemWindows = true

                    contentScrim = ColorDrawable(colorAttr(R.attr.colorPrimary))

                    toolbar(ThemeOverlay_AppCompat_Dark_ActionBar) {
                        id = TOOLBAR
                        popupTheme = ThemeOverlay_AppCompat_Light
                    }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize), init = collapseModeParams())

                }.lparams(width = matchParent, height = matchParent) {
                    scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED

                }
            }.lparams(width = matchParent, height = dimen(R.dimen.app_bar_height))

            nestedScrollView {
                id = DETAIL_CONTAINER
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            floatingActionButton {
                id = FAB
                imageResource = android.R.drawable.stat_notify_chat
            }.lparams {
                gravity = CENTER_VERTICAL or START
                margin = dimen(R.dimen.fab_margin)
                anchorId = DETAIL_CONTAINER
                anchorGravity = Gravity.TOP or Gravity.END
            }
        }
    }

}