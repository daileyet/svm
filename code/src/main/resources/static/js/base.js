/**
 * @author dailey.dai@openthinks.com
 * @date 2018/08/15
 */
if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
	}
}

function isBlank(pString){
    if (!pString || pString.length == 0) {
        return true;
    }
    // checks for a non-white space character 
    // which I think [citation needed] is faster 
    // than removing all the whitespace and checking 
    // against an empty string
    return !/[^\s]+/.test(pString);
}

window.getBaseUrl = function(subUrl) {
	var baseUrl = $("#page-base-url").attr("href");
	var url = baseUrl;
	if (subUrl && subUrl != '') {
		url = baseUrl + subUrl;
	}
	return url;
}

window.goBack = function() {
	window.history.back();
}

$(document).ready(function() {
	// notify
	var $notifyInfo = $("#page-forward-msg"),
		$notifyInfoType = $("#page-forward-msg-type");
	if ($notifyInfo.length > 0) {
		var msgType = $notifyInfoType.val() == 'fail' ? 'danger' : 'success',
			msgIcon = (msgType == 'danger') ? 'exclamation-sign' : 'ok-sign';
		var myMessager = new $.zui.Messager({
			type: msgType,
			icon: msgIcon
		});
		myMessager.show($notifyInfo.val());
	}
});

window.registerSetIframeHeightAuto = function(id, add) {
	try {
		var iframe = document.getElementById(id);
		var addtionH = 0;
		if (add) {
			try {
				addtionH = parseInt(add);
			} catch (e) {}
		}
		if (iframe.attachEvent) {
			iframe.attachEvent("onload", function() {
				iframe.height = iframe.contentWindow.document.documentElement.scrollHeight + addtionH;
			});
			return;
		} else {
			iframe.onload = function() {
				iframe.height = iframe.contentDocument.body.scrollHeight + addtionH;
			};
			return;
		}
	} catch (e) {
		throw new Error('setIframeHeight Error');
	}
}

window.setIframeHeightAuto = function(id, add) {
	try {
		var iframe = document.getElementById(id);
		var addtionH = 0;
		if (add) {
			try {
				addtionH = parseInt(add);
			} catch (e) {}
		}
		if (iframe.attachEvent) {
			iframe.height = iframe.contentWindow.document.documentElement.scrollHeight + addtionH;
			return;
		} else {
			iframe.height = iframe.contentDocument.body.scrollHeight + addtionH;
			return;
		}
	} catch (e) {
		throw new Error('setIframeHeight Error');
	}
}
