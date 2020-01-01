var editor = new Simditor({
	textarea: $('#content'),
	toolbar: [
		'title',
		'bold',
		'italic',
		'underline',
		'strikethrough',
		'fontScale',
		'color',
		'ol',
		'ul',
		'blockquote',
		'code',
		'table',
		'link',
		'image',
		'hr',
		'indent',
		'outdent',
		'alignment'
	],
	toolbarFloat: true,
	toolbarFloatOffset: 0,
	toolbarHidden: false,
	pasteImage: false,
	cleanPaste: false
	//optional options
});

var sHtml_Attr_Temp = '' +
	'<div class="attr-item">' +
	'<input type="hidden" name="attrP" value="{ATTR_P}"/>' +
	'<div class="form-group row ">' +
	'<div class="col-md-5">' +
	'<input type="text" maxlength="50" name="attrN" value="{ATTR_N}" class="form-control" placeholder="属性名" disabled>' +
	'</div>' +
	'<div class="col-md-5">' +
	'<input type="text" maxlength="100" name="attrV" value="{ATTR_V}" class="form-control" placeholder="属性值" disabled>' +
	'</div>' +
	'<div class="col-md-2" style="margin-top:3px">' +
	'<button type="button" class="btn btn-danger btn-sm">删除</button>' +
	'</div>' +
	'</div>' +
	'</div>';

(function() {

	$('.release-attr').on('hidden.zui.modal', function() {
		$('#attrN').val('');
		$('#attrV').val('');
	})

	var delAttr = function(){
		var $dom = $(this);
		$dom.parents(".attr-item").remove();
	}
	$(".attr-region .btn-danger").click(delAttr);
	$("#btnReleaseAttrAdd").click(function() {
		var sAttrN = $('#attrN').val(),
			sAttrV = $('#attrV').val(),
			sAttrP = sAttrN + "#" + sAttrV;
		if (isBlank(sAttrN)) {
			new $.zui.Messager('属性名不能为空！', {
				icon:'warning-sign',
				type: 'warning' // 定义颜色主题
			}).show();
			return;
		}
		if (isBlank(sAttrV)) {
			new $.zui.Messager('属性值不能为空！', {
				icon:'warning-sign',
				type: 'warning' // 定义颜色主题
			}).show();
			return;
		}
		var sHtml = sHtml_Attr_Temp.replace("{ATTR_P}", sAttrP).replace("{ATTR_N}", sAttrN).replace("{ATTR_V}", sAttrV);
		var $attr = $(sHtml);
		$(".btn-danger",$attr).click(delAttr);
		$('.release-attr').modal('hide');
		$(".attr-region").append($attr);
	});
})();
