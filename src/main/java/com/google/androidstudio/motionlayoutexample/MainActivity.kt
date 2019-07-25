package com.google.androidstudio.motionlayoutexample

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.androidstudio.motionlayoutexample.fragmentsdemo.FragmentExample2Activity
import com.google.androidstudio.motionlayoutexample.fragmentsdemo.FragmentExampleActivity
import com.google.androidstudio.motionlayoutexample.viewpagerdemo.ViewPagerActivity
import com.google.androidstudio.motionlayoutexample.viewpagerdemo.ViewPagerActivity2
import com.google.androidstudio.motionlayoutexample.youtubedemo.YouTubeDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var doShowPaths = false

    private val dataset: Array<DemosAdapter.Demo> = arrayOf(
            DemosAdapter.Demo("两约束布局文件切换过渡 (1/3)", "Basic motion example using referenced ConstraintLayout files", R.layout.motion_01_basic),
            DemosAdapter.Demo("两约束布局文件切换过渡-在一个scene.xml场景中 (2/3)", "Basic motion example using ConstraintSets defined in the MotionScene file", R.layout.motion_02_basic),
            DemosAdapter.Demo("两布局切换onTouchUp-停止 (3/3)", "Basic motion example same as 2, but autoComplete is set to false in onSwipe", R.layout.motion_02_basic_autocomplete_false),
            DemosAdapter.Demo("显示颜色插值-自定义属性", "Show color interpolation (custom attribute)", R.layout.motion_03_custom_attribute),
            DemosAdapter.Demo("显示图片淡入淡出 (1/2)", "Show image cross-fade (using ML's ImageFilterView + custom attribute)", R.layout.motion_04_imagefilter),
            DemosAdapter.Demo("显示图片饱和过度(2/2)", "Show image saturation transition (using ML's ImageFilterView + custom attribute)", R.layout.motion_05_imagefilter),
            DemosAdapter.Demo("使用一个简单的关键帧插值运动变化 (1/3)", "Use a simple keyframe to change the interpolated motion", R.layout.motion_06_keyframe),
            DemosAdapter.Demo("更复杂的关键帧,添加旋转插补 (2/3)", "More complex keyframe, adding rotation interpolation", R.layout.motion_07_keyframe),
            DemosAdapter.Demo("使用关键帧周期 (3/3)", "Basic example of using a keyframe cycle ", R.layout.motion_08_cycle),
            DemosAdapter.Demo("在CoordinatorLayout下作用于CollapsibleToolbar上 (1/3)", "Basic example of using MotionLayout instead of AppBarLayout", R.layout.motion_09_coordinatorlayout),
            DemosAdapter.Demo("在CoordinatorLayout下作用于CollapsibleToolbar上 (2/3)", "Slightly more complex example of MotionLayout replacing AppBarLayout, with multiple elements and parallax background", R.layout.motion_10_coordinatorlayout),
            DemosAdapter.Demo("在CoordinatorLayout下作用于CollapsibleToolbar上 (3/3)", "Another AppBarLayout replacement example", R.layout.motion_11_coordinatorlayout),
            DemosAdapter.Demo("DrawerLayout 抽屉布局之作用于DrawerContent (1/2)", "Basic DrawerLayout with MotionLayout", R.layout.motion_12_drawerlayout),
            DemosAdapter.Demo("DrawerLayout 抽屉布局之作用于DrawerContent (2/2)", "Advanced DrawerLayout with MotionLayout", R.layout.motion_13_drawerlayout),
            DemosAdapter.Demo("侧滑SidePanel内容双约束", "Side Panel, implemented with MotionLayout only", R.layout.motion_14_side_panel),
            DemosAdapter.Demo("控制多张图视觉差切换", "Parallax background. Drag the car.", R.layout.motion_15_parallax),
            DemosAdapter.Demo("ViewPager视觉差切换", "Using MotionLayout with ViewPager", ViewPagerActivity::class.java),
            DemosAdapter.Demo("ViewPager视觉差切换Lottie", "Using MotionLayout and Lottie with ViewPager", ViewPagerActivity2::class.java),

            DemosAdapter.Demo("Complex Motion Example (1/4)", "Basic CoordinatorLayout-like behavior. Implemented with MotionLayout only, using a moving guideline. Note the view isn't resized. ", R.layout.motion_17_coordination),
            DemosAdapter.Demo("Complex Motion Example (2/4)", "Advanced CoordinatorLayout-like behavior (adding a FAB). Implemented with MotionLayout only, using a moving guideline. Note the view isn't resized.", R.layout.motion_18_coordination),
            DemosAdapter.Demo("Complex Motion Example (3/4)", "Advanced CoordinatorLayout-like behavior (adding a FAB). Implemented with MotionLayout only, using direct resizing of the view.", R.layout.motion_19_coordination),
            DemosAdapter.Demo("Complex Motion Example (4/4)", "Advanced Synchronized reval motion + helper (bounce). Implemented with MotionLayout only.", R.layout.motion_20_reveal),
            DemosAdapter.Demo("Fragment Transition Example (1/2)", "Example showing transitioning fragments within MotionLayout", FragmentExampleActivity::class.java),
            DemosAdapter.Demo("Fragment Transition Example (2/2)", "Example showing transitioning fragments within MotionLayout", FragmentExample2Activity::class.java),
            DemosAdapter.Demo("YouTube like motion Example", "Example showing a transition like YouTube", YouTubeDemoActivity::class.java),
            DemosAdapter.Demo("Example using KeyTrigger", "Example that calls a method using KeyTrigger", R.layout.motion_25_keytrigger),
            DemosAdapter.Demo("Example using Multi State", "Example that transitions between multiple states", R.layout.motion_26_multistate)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        viewAdapter = DemosAdapter(dataset)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        showPaths.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(p0: CompoundButton?, value: Boolean) {
        doShowPaths = value
    }

    fun start(activity: Class<*>, layoutFileId: Int) {
        val intent = Intent(this, activity).apply {
            putExtra("layout_file_id", layoutFileId)
            putExtra("showPaths", doShowPaths)
        }
        startActivity(intent)
    }

}
