package fr.webedia.spawn.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.util.*

class FragmentUtils {

    companion object {

        private var tagList: MutableList<String> = ArrayList()

        fun addOrResurfaceFragment(fragmentManager: FragmentManager, @IdRes container: Int, fragment: Fragment, tag: String) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            hideAllFragments(fragmentManager, fragmentTransaction)
            if (tagList.contains(tag)) {
                val f = fragmentManager.findFragmentByTag(tag)
                if (f != null) fragmentTransaction.show(f)
                else {
                    fragmentTransaction.add(container, fragment, tag)
                    tagList.add(tag)
                }
            } else {
                fragmentTransaction.add(container, fragment, tag)
                tagList.add(tag)
            }

            fragmentTransaction.commit()
        }

        private fun hideAllFragments(fragmentManager: FragmentManager, fragmentTransaction: FragmentTransaction) {
            for (s in tagList) {
                val fragment = fragmentManager.findFragmentByTag(s)
                if (fragment != null) {
                    fragmentTransaction.hide(fragment)
                }
            }
        }

    }

}