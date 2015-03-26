# DigitalTimer简介 #
- 在样式上DigitalTimer支持自定义文字背景，大小，颜色的自定义。
- 在功能上DigitalTimer支持启动计时，计时暂停等功能。
 
## 运行效果图 ##
![运行效果图](https://github.com/crazycodeboy/DigitalTimer/blob/master/raw/%E6%95%B0%E5%AD%97%E8%AE%A1%E6%97%B6%E5%99%A8.gif?raw=true)


## XML Attributes ##
<table>
<tbody>

<tr><td><em>Attribute 	Related </em></td><td><em>Related Method</em></td><td><em>Description</em></td></tr>
<tr><td>app:textColor	 </td><td>setBaseTime(long baseTime)</td><td>设置基准时间</td></tr>
<tr><td>app:textBgRes</td><td>setTextBgRes(int textBgRes)</td><td>设置文字背景</td></tr>
<tr><td>app:textSize</td><td>etTextSize(float textSize)</td><td>设置文字大小</td></tr>

</tbody>
</table>


## 使用方法 ##
1.  ```java public void setBaseTime(long baseTime)```设置基准时间
2.  ```java public void start()```开始计时。
3. ```java public void stop()```停止计时。
4. 其它使用细节可以参照实例。



