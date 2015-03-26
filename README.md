# DigitalTimer简介 #
- 在样式上DigitalTimer支持自定义文字背景，大小，颜色的自定义。
- 在功能上DigitalTimer支持启动计时，计时暂停等功能。
 
## 运行效果图 ##
![运行效果图](https://github.com/crazycodeboy/GroupListView/blob/dev/raw/%E8%BF%90%E8%A1%8C%E6%95%88%E6%9E%9C%E5%9B%BE.gif?raw=true)


## XML Attributes ##
<table>
<tbody>
<tr><td><em>Year</em></td><td><em>Temperature (low)</em></td><td><em>Temperature (high)</em></td></tr>
<tr><td>1900</td><td>-10</td><td>25</td></tr>
<tr><td>1910</td><td>-15</td><td>30</td></tr>
<tr><td>1920</td><td>-10</td><td>32</td></tr>
</tbody>
</table>

>为了实现支付宝账单列表的悬停、上拉加载、下拉刷新的效果，我从Git上Fetch了pulltorefresh和PinnedSectionList两个开源项目，并对pulltorefresh进行修改实现上拉加载的效果，将修改后的pulltorefresh更名成了ListViewPlus，最后将PinnedSectionList的library依赖于ListViewPlus,就实现了带有悬停效果并且支持上拉加载、下拉刷新的GroupListView,现在将其开源出来供大家使用。

## 使用方法 ##
1.  GroupListView是基于ListView开发的一个控件，所以大家可以使用ListView的方式来使用它。
2.  ListViewPlus部分：关于ListViewPlus方面的功能大家可以参考[https://github.com/crazycodeboy/ListViewPlus](https://github.com/crazycodeboy/ListViewPlus "ListViewPlus")
3.  PinnedSectionList部分：

 PinnedSectionList的使用需要一个实现如下方法的适配器：

```java
@Override
public int getItemViewType(int position) {
	return items.get(position).getType();
}
@Override
public int getViewTypeCount() {
	return 2;
}
@Override
public boolean isItemViewTypePinned(int viewType) {
	return viewType==Item.SECTION;
}
```

4 .其它使用细节可以参照实例。


