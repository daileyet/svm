/**
 * @author dailey.dai@openthinks.com
 * @date 2019/04/24
 */
var cols = [
	{
		name: 'category',
		label: '类别',
		width: 0.2
	},
	{
		name: 'attr_name',
		label: '属性名',
		width: 0.3,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return dataValue == null || dataValue == 'null' ? '' : dataValue;
			}
		}
	},
	{
		name: 'attr_display',
		label: '属性显示',
		width: 0.3,
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
				editUrl = getBaseUrl('attr/edit/' + v_id),
				modContent = ' <a  href="' + editUrl + '" title="更新"><i class="icon icon-pencil"></i></a>';
				return modContent;
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
			var urlBase = getBaseUrl('api/attr/search');
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

