(function(){
	$("#valid").click(function(){
		var $valid = $(this);
		$valid.val($valid.prop('checked')?1:0);
	});
	
	$('#delReleaseConfirm').on('show.zui.modal', function (e) {
	  var $button = $(e.relatedTarget);
	  var vSessionId = $button.data("session"),vType=$button.data("type");
	  $(".label-release-type").text(vType);
	  var $del = $(".release-del"),vHref = $del.data("link")+"?id="+vSessionId;
	  $del.attr("href",vHref);
	})
})()

var editor = new Simditor({
  textarea: $('#description')
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