/**
 * @author dailey.dai@openthinks.com
 * @date 2019/03/15
 */
var cols = [
	{
		name: 'short_name',
		label: '项目缩写',
		width: 200
	},
	{
		name: 'full_name',
		label: '项目全称',
		width: 200,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return dataValue == null || dataValue == 'null' ? '' : dataValue;
			}
		}
	},
	{
		name: 'meta_releases',
		label: '关联模块',
		width: 0.4,
		html: true,
		sort: false,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				dataValue = ( dataValue == null || dataValue == 'null' ? '' : dataValue);
				var arrayModules = dataValue.split(":"),sHtml="";
				for(var index in arrayModules){
					var sModule = arrayModules[index];
					sHtml = sHtml+" <span class='label label-info label-outline'>"+sModule+"</span>";
				}
				return sHtml;
			}
		}
	},
	{
		name: 'option',
		label: '操作',
		width: 80,
		sort: false,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				var row = dataGrid.getDataItem(cell.rowIndex - 1),
				v_id = row['id'],
				editUrl = getBaseUrl('project/edit/' + v_id),
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
			var urlBase = getBaseUrl('api/project/search');
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

