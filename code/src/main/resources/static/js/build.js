/**
 * @author dailey.dai@openthinks.com
 * @date 2019/03/12
 */
// build version definitions
var build_cols = [{
		name: '',
		label: '',
		width: 30,
		checkbox: true,
		sort: false
	},
	{
		name: 'number',
		label: '版本号',
		width: 150
	},
	{
		name: 'valid',
		label: '状态',
		width: 55,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return (dataValue == 1 || dataValue == '1') ? '<span class="label label-success pointer">有效</span>' :
					'<span class="label label-warning pointer">无效</span>';
			}
		}
	},
	{
		name: 'create_date',
		label: '创建时间',
		width: 130,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return dataValue == null || dataValue == 'null' ? '' : dataValue;
			}
		}
	},
	{
		name: 'release_infos',
		label: '模块信息',
		width: 0.4,
		html: true,
		sort: false,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				dataValue == null || dataValue == 'null' ? '' : dataValue;
				var sFormat = "";
				var infos = dataValue.split(";");
				for (var index in infos) {
					var info = infos[index];
					var info_ = info.split(":");
					var info_type = info_[0] || '',
						info_num = info_[1] || '';
					sFormat = sFormat + '<span class="font-size-10 pointer label label-badge label-info"  title="' + info_type +
						'">' + info_num + '</span> '
				}
				return sFormat;
			}
		}
	},
	{
		name: 'option',
		label: '操作',
		width: 75,
		sort: false,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				var row = dataGrid.getDataItem(cell.rowIndex - 1),
					v_number = row['number'],
					viewUrl = getBaseUrl('build/view/' + v_number),
					vivContent = '<a  href="' + viewUrl + '" title="查看"><i class="icon icon-file-o"></i></a>',
					exportUrl = getBaseUrl('api/build/export/' + v_number),
					exportContent = ' <a  href="' + exportUrl + '" title="导出XML"><i class="icon icon-file-excel"></i></a>',
					editUrl = getBaseUrl('build/edit/' + v_number),
					modContent = ' <a  href="' + editUrl + '" title="更新"><i class="icon icon-pencil"></i></a>';
				var isEditable = $("#_ROLE_").length > 0;
				if (!isEditable)
					return vivContent + exportContent;
				return vivContent + exportContent + modContent;
			}
		}
	}
];
///////////////////////////////////////
(function() {
	$('#datagridLog').datagrid({
		height: 'page',
		checkable: true,
		checkByClickRow: false,
		dataSource: {
			cols: build_cols,
			//array: getLogData()
			remote: function(params, datagrid) {
				var urlBase = getBaseUrl('api/build/search');
				return {
					url: urlBase,
					// 请求类型
					type: 'GET',
					// 数据类型
					dataType: 'json'
				};
			}
		},
		states: {
			pager: {
				page: 1,
				recPerPage: 15
			},
			search: $('#inputSearchLog').val()
		},
		//			   hoverCell:true,
		sortable: true,
		autoOnSearchChange: false,
		extendFilterParams: function(params) {
			if (params) {
				params.startTime = $("#startTime").val();
				params.endTime = $("#endTime").val();
			}
		},
		afterLoad: function() {
			$('[data-toggle="tooltip"]').tooltip();
		}
	});
	// 选择时间和日期
	$(".form-datetime").datetimepicker({
		weekStart: 1,
		todayBtn: 1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		showMeridian: 1,
		format: "yyyy-mm-dd hh:ii"
	});

	$("#searchButton").unbind("click").bind("click", function() {
		var myDataGrid = $('#datagridLog').data('zui.datagrid');
		myDataGrid.search($('#inputSearchLog').val());
	});

	var myMessager = new $.zui.Messager({
		type: 'warning',
		icon: 'warning-sign'
	});
	$("#btn-preview").unbind("click").bind("click", function() {
		var myDataGrid = $('#datagridLog').data('zui.datagrid');
		// 获取当前已选中的行数据项
		var selectedItems = myDataGrid.getCheckItems();
		if (!selectedItems || selectedItems.length == 0) {
			myMessager.show('请至少勾选一个版本号！');
			return;
		}
		
		var url = $(this).data("link")+"?";
		for (var k in selectedItems) {
			if(selectedItems[k]){
				url = url+'vids='+selectedItems[k].id+'&';
			}
		}
		window.open(url);
	});


})();
