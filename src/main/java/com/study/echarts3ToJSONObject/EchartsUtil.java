package com.study.echarts3ToJSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * @author yangchuan
 * @date 2016-08-26
 *
 */
public class EchartsUtil {
		/**
		 * 无数据时默认返回的Option
		 */
		public static JSONObject NO_DATE_OPTION=JSONObject.fromObject("{title: {"+
																	        "text: '没有数据',"+
																	    "}}");
		/**
		 * 默认的工具栏：
		 * 可切换折线和柱状两种图形
		 */
		public static JSONObject DEFAULT_TOOLBOX =JSONObject.fromObject(
					"{show : false,feature : {dataView : {show: false, readOnly: false},"+
	                                "magicType : {show: true, type: ['line', 'bar']},restore : {show: true},"+
	                                "saveAsImage : {show: true}}}");

		/**
		 * 默认的工具提示
		 */
		public static JSONObject DEFAULT_TOOTIP=JSONObject.fromObject("{trigger: 'axis'}");
		
		/**
		 * 设置简单的工具提示
		 * @param trigger
		 * @param axisPointerType
		 * @param formatter
		 * @return
		 */
		public static JSONObject setTooltip(String trigger,String axisPointerType,String formatter){
			JSONObject obj =new JSONObject();
			obj.put("trigger", trigger);
			JSONObject axisPointer =new JSONObject();
			axisPointer.put("type", axisPointerType);
			obj.put("axisPointer", axisPointer);
			obj.put("formatter", formatter);
			return obj;
		}

		/**
		 * 设置title
		 * @param tittleText 
		 * @param subText
		 * @param textfontSize
		 * @param subTextfontSize
		 * @return
		 */
		public static JSONObject setTittle(String tittleText,String subText,Integer textfontSize,Integer subTextfontSize){
			JSONObject obj =new JSONObject();
			if(StringUtils.isNotBlank(tittleText)){
				obj.put("text", tittleText);
			}
			if(StringUtils.isNotBlank(subText)){
				obj.put("subtext", subText);
			}
			JSONObject textStyle =new JSONObject();
			textStyle.put("fontSize", textfontSize);
			JSONObject subTextStyle =new JSONObject();
			subTextStyle.put("fontSize", subTextfontSize);
			obj.put("textStyle", textStyle);
			obj.put("subTextStyle", subTextStyle);
			return obj;
		}
		/**
		 * 设置legend
		 * @param legends 名称
		 * @param top 距离容器顶部距离(可设置成像素值和百分比)
		 * @param right距离容器右边距的距离(可设置成像素值和百分比)
		 * @param closeLegendIndex 默认不显示的
		 * @return
		 */
        public static JSONObject setLegendAndLocationAndCloseSome(List<String> legends,String top,String right,Integer[] closeLegendIndex){
			JSONObject obj =new JSONObject();
			obj.put("data", legends);
			if(null!=right){
				obj.put("right", right);
			}
			if(null!=top){
				obj.put("top", top);
			}
			if(null!=closeLegendIndex){
				JSONObject closeObj =new JSONObject();
				for(Integer index : closeLegendIndex){
					closeObj.put(legends.get(index), false);
				}
				obj.put("selected", closeObj);
			}
			return obj;
		}
        /**
         * 设置grid
         * @param left
         * @param right
         * @param top
         * @param bottom
         * @param containLabel:grid 区域是否包含坐标轴的刻度标签，
         * 在无法确定坐标轴标签的宽度，容器有比较小无法预留较多空间的时候，
         * 可以设为 true 防止标签溢出容器。
         * @return
         */
    	public static JSONObject setGrid(String left,String right,String top,String bottom,Boolean containLabel){
    		JSONObject obj =new JSONObject();
    		obj.put("left", left);
    		obj.put("right", right);
    		obj.put("top", top);
    		obj.put("bottom", bottom);
    		obj.put("containLabel", containLabel);
    		return obj;

    	}
    	/**
    	 * 设置tXAxis
    	 * @param xItems data
    	 * @param type：
    	 * 		'value' 数值轴，适用于连续数据。
		 *		'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
		 *		'time' 时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。
		 *		'log' 对数轴。适用于对数数据。
    	 * @param name
    	 * @return
    	 */
    	public static JSONObject setXAxis(String [] data,String type,String name){
    		JSONObject rst = new JSONObject();
    		rst.put("type", type);
    		rst.put("data", data);
    		rst.put("name", name);
    		return rst;
    	}
    	/**
    	 * 
    	 * @param ① names                 : 设置y轴的名称的数组,这个名称将与series中的名称对应。size必须>0
    	 * @param ② min                   : 设置y轴最小值的数组  
    	 * @param ③ max                   : 设置y轴最大值的数组
    	 * @param ④setMinAndMaxindex	  : 设置Y轴最大最小值对应names数组的索引
    	 * @param ⑤ splitNumber	                         ：设置Y轴分割成多少段：在类目轴中无效
    	 * @param ⑥setSplitNumberIndexs   ：设置y轴分割段数对应的names数组中的索引
    	 * 注意：
    	 * ② ③ ④的size必须相同，⑤ ⑥的size必须相同
    	 * y轴的Type默认是数值：value
    	 * @return
    	 */
		public static List<JSONObject> setYAxis(List<String> names,List<String> min,List<String> max,List<Integer> setMinAndMaxindex,List<Integer> splitNumber, List<Integer> setSplitNumberIndexs){
			List<JSONObject> res = new ArrayList<JSONObject>();
			Integer minAndMaxIndex=0;
			Integer splitNumberIndex=0;
			for(int i=0;i<names.size();i++){
				String name = names.get(i);
				JSONObject obj =new JSONObject();
				obj.put("type", "value");
				obj.put("name", name);
				if(setMinAndMaxindex.contains(i)){
					obj.put("min", min.get(minAndMaxIndex));
					obj.put("max", max.get(minAndMaxIndex));
					minAndMaxIndex++;
				}
				if(null!=setMinAndMaxindex && null!=splitNumber && setSplitNumberIndexs.contains(i)){
					obj.put("splitNumber", splitNumber.get(splitNumberIndex));
					splitNumberIndex++;
				}
				res.add(obj);
			}
			return res;
		}
		/**
		 * 设置option中的任何一个属性：key为属性名；此处请参照Echarts baidu官方文档
		 * @param titleItem 属性名
		 * 如果属性值为对象:建议将其封装成JSONObject
		 * 如果属性值为数组:建议将其封装成ArrayList
		 * @return
		 */
		public static JSONObject setOptinItem(Map<String,Object> titleItem){
			JSONObject obj =new JSONObject();
			for (Map.Entry<String, Object> entry : titleItem.entrySet()) {
				obj.put(entry.getKey(), entry.getValue());
			}
			return obj;
		}
}
