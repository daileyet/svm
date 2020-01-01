/**
 * @author dailey.dai@openthinks.com
 * @date 2019/03/12
 */
// 选择时间和日期
$(".form-date").datetimepicker(
{
    language:  "zh-CN",
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0,
    format: "yymmdd",
	startDate:new Date(),
	pickerPosition:'bottom-left'
});

(function(){
var fn_cvt=function(){
	var $seq_number = $('#seq_number'),seq_number_val = $seq_number.val();
	try{
		var val_10 = parseInt(seq_number_val),val_16=0;
		if(val_10>=100){
			val_16 = val_10.toString(16).toUpperCase();
			$seq_number.siblings('.input-group-addon').text('0x'+val_16);
		}else{
			$seq_number.siblings('.input-group-addon').text(val_10);
		}
	}catch(e){
	}	
}
/**
* Build number template:
* <project short name>_<sequence>_<date format>_<project meta business id><productline meta business id>
*/
var val_parts = ['XXXX','XX','YYMMDD','X','XX'];
var fn_getBuildNo = function(){
	val_parts[0]=$("[data-part='0'] option:selected").text();
	val_parts[1]=$("[data-part='1']").val();
	val_parts[2]=$("[data-part='2']").val();
	val_parts[3]=$("[data-part='3']").val();
	val_parts[4]=$("[data-part='4']").val();
	if(val_parts[1].length==1){
		val_parts[1] = '0'+val_parts[1];
	}
	try{
		val_parts[3] = parseInt(val_parts[3]);
	}catch(e){console.log(e)}
	var s_buildNo=val_parts[0]+'_'+val_parts[1]+'_'+val_parts[2]+'_'+val_parts[3]+val_parts[4];
	return s_buildNo;
}

var fn_part_change=function(){
	$('#build_no').val(fn_getBuildNo());
}

var fn_prj_id_change_load_productline=function(){
	var $prj = $("#prj"),sUrl= $prj.data("link"),sPrjId = $prj.val();
	sUrl = sUrl+"?prjId="+sPrjId;
	$(".productline-loading").show();
	$.ajax({url: sUrl, success: function(result){
		var arryCarlines = result.productlines || [];
			setTimeout(function(){
				$("#productline option").remove();
				for(var index in arryCarlines){
					var productline = arryCarlines[index];
					$("#productline").append("<option value='"+productline.pl_id+"'>"+productline.pl_name+"</option>");
				}
				$(".productline-loading").hide();
				fn_part_change();
			},800);
	},error:function(){
		$(".productline-loading").hide();
	}});
}

var fn_prj_id_change_load_release=function(){
	var $prj = $("#prj_id"),$prjOpt = $("#prj_id option:selected"),sUrl= $prjOpt.data("link");
	$(".release-loading").show();
	$.ajax({url: sUrl, success: function(result){
		var arrys = result.data || [];
		setTimeout(function(){
				$(".release-region").each(function(){
					var $region = $(this),sReleaseType=  $region.data("role");
					var needed = false;
					for(var index in arrys){
						var sReleaseType_ = arrys[index].name;
						if(sReleaseType_ && sReleaseType_==sReleaseType){
							needed=true;
						}
					}
					if(!needed){
						$("label",$region).removeClass("required");
						$("select",$region).prop("required",false);
						$region.addClass("hidden");
					}else{
						$("label",$region).addClass("required");
						$("select",$region).prop("required",true);
						$region.removeClass("hidden");
					}
				});
				$(".release-loading").hide();
			},800);
	},error:function(){
		$(".release-loading").hide();
	}});
}

var fn_prj_short_change_load_seq=function(){
	var $prj = $("#prj_id"),sUrl= $prj.data("link"),sPrjShortName = $prj.find('option:selected').text();
	sUrl = sUrl+"?prj_short="+sPrjShortName;
	$(".seq-loading").show()
	$.ajax({url: sUrl, success: function(result){
		setTimeout(function(){
			var vSeq = result.nextVal || 1;
			$("#seq_number").val(vSeq);
			$(".seq-loading").hide();
			fn_cvt();
			fn_part_change();
		},800);
	},error:function(){
		$(".seq-loading").hide();
	}});
}

var fn_bind=function(){
	$("#seq_number").change(fn_cvt);
	$("[data-part]").change(fn_part_change);
	$("#prj").change(fn_prj_id_change_load_productline);
	$("#prj_id").change(fn_prj_id_change_load_release);
	$("#prj_id").change(fn_prj_short_change_load_seq);
}
var fn_init=function(){
	fn_bind();
	fn_cvt();
	fn_prj_short_change_load_seq();
	fn_prj_id_change_load_productline();
	fn_prj_id_change_load_release();
	//$('#build_no').val(fn_getBuildNo());
}

fn_init();
})();

var editor = new Simditor({
  textarea: $('#desc')
	,toolbar: [
  'title',
  'bold',
  'italic',
  'underline',
  'strikethrough',
  'fontScale',
  'color',
  'ol' ,
  'ul' ,
  'blockquote',
  'code',
  'table',
  'link',
  'image',
  'hr',
  'indent',
  'outdent',
  'alignment'
]
  ,toolbarFloat: true
  ,toolbarFloatOffset: 0
  ,toolbarHidden: false
  ,pasteImage: false
  ,cleanPaste: false
  //optional options
});