相关组件demo  见GitHub：https://github.com/dingzuoqiang/
# FormNormal
android 开发过程中 最常见的 表单 封装，
```
            <com.dzq.widget.FormNormal
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                app:fnTitle="title" />

            <com.dzq.widget.FormNormal
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                app:fnHint="hint"
                app:fnTitle="title" />

            <com.dzq.widget.FormNormal
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                app:fnText="text"
                app:fnTitle="title" />

            <com.dzq.widget.FormNormal
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                app:fnHint="hint"
                app:fnIndicatorVisible="false"
                app:fnTitle="title" />

            <com.dzq.widget.FormNormal
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                android:background="@color/white"
                app:fnEditable="true"
                app:fnHint="hint"
                app:fnIndicatorVisible="false"
                app:fnTitle="title" />

            <com.dzq.widget.FormNormal
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                android:background="@color/white"
                app:fnEditable="true"
                app:fnGravityLeft="true"
                app:fnHint="hint"
                app:fnIndicatorVisible="false"
                app:fnTitle="title" />

            <com.dzq.widget.FormNormal
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                android:background="@color/white"
                app:fnEditable="true"
                app:fnGravityLeft="true"
                app:fnHint="hint"
                app:fnSetClearable="true"
                app:fnTitle="title" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="@dimen/padding_15"
                android:text="FormNormal2 用法介绍"
                android:textColor="@color/color_333333"
                android:textSize="@dimen/text_size_20" />

            <com.dzq.widget.FormNormal2
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                app:fn2IndicatorVisible="false"
                app:fn2Title="title"
                app:fn2Title2="title2" />

            <com.dzq.widget.FormNormal2
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                app:fn2Title="title"
                app:fn2Title2="title2" />

            <com.dzq.widget.FormNormal2
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                app:fn2Hint="hint"
                app:fn2Title="title"
                app:fn2Title2="title2" />

            <com.dzq.widget.FormNormal2
                style="@style/FormNormalBaseStyle"
                android:layout_marginTop="@dimen/padding_10"
                app:fn2Text="text"
                app:fn2Title="title"
                app:fn2Title2="title2" />
```
# CenterAlignImageSpan
图片和文字垂直居中
```
Drawable drawable = mContext.getResources().getDrawable(R.drawable.icon_wd_tupian_3x);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            CenterAlignImageSpan imgSpan = new CenterAlignImageSpan(drawable);
            SpannableString spanString = new SpannableString(question.title + "  ");
            spanString.setSpan(imgSpan, question.title.length() + 1, question.title.length() + 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mHolder.tvTitle.setText(spanString);

```
# TopAlignSuperscriptSpan
顶部对齐
```
SpannableString msp = new SpannableString(" 99+");
            msp.setSpan(new TopAlignSuperscriptSpan(0.6f, 0f), 3, 4,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvHouse.setText(msp);
```
# CustomRatingBar
评分组件
```
            <com.dzq.widget.CustomRatingBar
                android:id="@+id/lay_star_level"
                android:layout_width="200dp"
                android:layout_height="20dp"
                android:numStars="7"
                android:rating="2.5"
                android:isIndicator="true" />

                    <style name="CustomRatingBarStyle">
                        <item name="normalDrawable">@drawable/icon_xq_star_2_4x</item>
                        <item name="selectDrawable">@drawable/icon_xq_star_1_4x</item>
                        <item name="android:stepSize">0.1</item>
                        <item name="android:numStars">5</item>
                    </style>
```
# CustomBannerView
一个简单方便的轮播图组件，基于viewpager 基础上进行的封装。
```
<com.dzq.widget.CustomBannerView
        android:id="@+id/banner"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        app:autoPlayDuration="2000"
        app:defaultImage="@drawable/bg_banner"
        app:indicatorMargin="@dimen/indicatorMargin"
        app:indicatorPosition="rightBottom"
        app:indicatorShape="oval"
        app:indicatorSpace="@dimen/indicatorSpace"
        app:isAutoPlay="true"
        app:isIndicatorVisible="true"
        app:scrollDuration="1000"
        app:selectedIndicatorColor="@color/color_ec407a"
        app:selectedIndicatorHeight="5dp"
        app:selectedIndicatorWidth="5dp"
        app:unSelectedIndicatorColor="@color/color_71d9e7"
        app:unSelectedIndicatorHeight="5dp"
        app:unSelectedIndicatorWidth="5dp"

        />

    <com.dzq.widget.CustomBannerView
        android:id="@+id/banner2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:layout_weight="1"
        app:autoPlayDuration="2000"
        app:defaultImage="@drawable/bg_banner"
        app:indicatorMargin="@dimen/indicatorMargin"
        app:indicatorPosition="rightBottom"
        app:indicatorShape="rect"
        app:indicatorSpace="@dimen/indicatorSpace"
        app:isAutoPlay="false"
        app:isIndicatorVisible="true"
        app:scrollDuration="1000"
        app:selectedIndicatorColor="@color/color_ec407a"
        app:selectedIndicatorHeight="5dp"
        app:selectedIndicatorWidth="10dp"
        app:unSelectedIndicatorColor="@color/color_71d9e7"
        app:unSelectedIndicatorHeight="10dp"
        app:unSelectedIndicatorWidth="5dp"

        />

    <com.dzq.widget.CustomBannerView
        android:id="@+id/banner3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:layout_weight="1"
        app:cornerRadii="5dp"
        app:indicatorMargin="@dimen/indicatorMargin"
        app:indicatorPosition="centerTop"
        app:indicatorShape="rect"
        app:indicatorSpace="@dimen/indicatorSpace"
        app:isAutoPlay="false"
        app:isIndicatorVisible="true"
        app:maskEndColor="#00000000"
        app:maskStartColor="#99000000"
        app:scrollDuration="1000"
        app:selectedIndicatorColor="#00CAA9"
        app:selectedIndicatorHeight="10dp"
        app:selectedIndicatorWidth="25dp"
        app:unSelectedIndicatorColor="#26000000"
        app:unSelectedIndicatorHeight="10dp"
        app:unSelectedIndicatorWidth="10dp" />


```