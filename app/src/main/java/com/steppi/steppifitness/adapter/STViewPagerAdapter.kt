package com.steppi.steppifitness.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.ArrayList

class STViewPagerAdapter : FragmentStatePagerAdapter {
    private var mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    constructor(manager: FragmentManager) : super(manager) {}

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    constructor(manager: FragmentManager, mFragmentList: ArrayList<Fragment>) : super(manager) {
        this.mFragmentList = mFragmentList
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    fun addFrag(fragment: Fragment) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add("")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun clearData() {
        if (mFragmentList.size > 0) {
            mFragmentList.clear()
        }
    }
}
