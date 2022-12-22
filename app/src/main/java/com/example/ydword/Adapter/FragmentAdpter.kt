package com.example.ydword.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdpter:FragmentPagerAdapter {

    var fragments: ArrayList<Fragment>? = null
    var strings = arrayOf("英语写译", "翻译写英")
    constructor(fm:FragmentManager,list:ArrayList<Fragment>):super(fm){
        this.fragments=list
    }

    override fun getItem(position:Int):Fragment{
        return fragments!![position]
    }

    override fun getCount(): Int {
        return fragments!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return strings[position]
    }
}





























