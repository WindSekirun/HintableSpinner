package pyxis.uzuki.live.hintablespinner

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatSpinner
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

class HintableSpinner constructor(context: Context, attrs: AttributeSet? = null) : AppCompatSpinner(context, attrs) {
    private val mDropdownList = ArrayList<String>()
    private var mHintText: String? = null
    private var mHintTextColor = R.color.default_color
    private var listener: OnItemSelectedListener? = null
    private var mDropdownResources = android.R.layout.simple_spinner_dropdown_item

    init {
        init(attrs)
    }

    /**
     * get selected item's text
     *
     * @return selected item, can be null
     */
    val itemSelected: String?
        get() {
            val position = selectedItemPosition
            if (position == mDropdownList.size) {
                return null
            }

            val dest = mDropdownList[position]

            return if (dest == mHintText) {
                null
            } else dest

        }

    /**
     * set callback of item seleected
     *
     * @param listener [OnItemSelectedListener]
     */
    fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        this.listener = listener
    }

    /**
     * add String into dropdown list
     *
     * @param items
     */
    fun addDropdownList(vararg items: String) {
        Collections.addAll(mDropdownList, *items)
        applyView()
    }

    /**
     * add String resource into dropdown list
     *
     * @param items
     */
    fun addDropdownList(vararg items: Int) {
        items.mapTo(mDropdownList) { context.getString(it) }

        applyView()
    }

    /**
     * set Resource of DropdownView
     */
    fun setDropDownViewResource(resource: Int) {
        this.mDropdownResources = resource
    }

    /**
     * clear selected state of spinner
     */
    fun clear() {
        setSelection(adapter.count)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, R.styleable.HintableSpinner)
            mHintText = array.getString(R.styleable.HintableSpinner_hintable_hintText)
            mHintTextColor = array.getResourceId(R.styleable.HintableSpinner_hintable_hintTextColor, R.color.default_color)
            array.recycle()
        }
    }

    private fun applyView() {
        val adapter = ListAdapter(mDropdownResources)
        adapter.setDropDownViewResource(mDropdownResources)
        adapter.addAll(mDropdownList)

        if (!TextUtils.isEmpty(mHintText)) {
            adapter.add(mHintText)
        }

        setAdapter(adapter)
        setSelection(adapter.count)
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                if (listener != null && i != mDropdownList.size) {
                    listener!!.onItemSelected(view, i, mDropdownList[i])
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                if (listener != null) {
                    listener!!.onNothingSelected()
                }
            }
        }
    }

    private inner class ListAdapter(resources: Int) : ArrayAdapter<String>(this@HintableSpinner.context, resources) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getView(position, convertView, parent)
            val textView = view.findViewById<TextView>(android.R.id.text1)

            if (position == count) {
                textView.text = ""
                textView.hint = getItem(count)
                textView.setHintTextColor(ContextCompat.getColor(this@HintableSpinner.context, mHintTextColor))
            }

            return view
        }

        override fun getCount(): Int {
            return super.getCount() - 1
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(view: View, position: Int, item: String)

        fun onNothingSelected()
    }
}