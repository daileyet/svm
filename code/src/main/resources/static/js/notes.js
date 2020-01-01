var rex = /(&)?style=\d/gi;
(function() {
	$("button[data-style]").unbind('click').click(function() {
		var $btn = $(this);
		var sUrl = window.location.href;
		var sUlrCls = sUrl.replace(rex, '');
		window.location.href = sUlrCls + '&style=' + $btn.data('style');
	});
})();
