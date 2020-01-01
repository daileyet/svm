/**
 * @author dailey.dai@openthinks.com
 * @date 2019/03/15
 */
var cols = [
	{
		name: 'release_num',
		label: '模块版本号',
		width: 0.2
	},
	{
		name: 'type',
		label: '模块名',
		width: 150,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return dataValue == null || dataValue == 'null' ? '' : dataValue;
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
		name: 'option',
		label: '操作',
		width: 100,
		sort: false,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				var row = dataGrid.getDataItem(cell.rowIndex - 1),
				v_id = row['id'],
				viewUrl = getBaseUrl('release/view/' + v_id),
				vivContent = '<a  href="' + viewUrl + '" title="查看"><i class="icon icon-file-o"></i></a>';
				editUrl = getBaseUrl('release/edit/' + v_id),
				modContent = ' <a  href="' + editUrl + '" title="更新"><i class="icon icon-pencil"></i></a>';
				var isEditable = $("#_ROLE_").length>0;
				if(!isEditable)
					return vivContent;
				return vivContent + modContent;
			}
		}
	}
];
///////////////////////////////////////
(function(){
$('#datagridLog').datagrid({
	height:'page',
	dataSource: {
		cols: cols,
		remote: function(params, datagrid) {
			var urlBase = getBaseUrl('api/release/search');
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
	autoOnSearchChange:false
});

$("#searchButton").unbind("click").bind("click",function(){
	var myDataGrid = $('#datagridLog').data('zui.datagrid');
	myDataGrid.search($('#inputSearchLog').val());
})


})();

