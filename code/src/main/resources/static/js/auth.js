/**
 * @author dailey.dai@openthinks.com
 * @date 2018/08/23
 */
var ROLES = ['','版本查看员','版本配置员','系统管理员'];
var getRoles=function(strRoles){
	if(strRoles==undefined || strRoles=='')
		return [];	
	var str = strRoles || '';
	var array = str.split(':');
	return array || [];
}
var auth_cols = [{
		name: 'id',
		label: 'ID',
		width: 0.2
	},
	{
		name: 'user_name',
		label: '系统用户',
		width: 0.3,
	},
	{
		name: 'user_roles',
		label: '用户角色',
		width: 0.3,
		sort: false,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				var roles = getRoles(dataValue),shtml='';
				for (var i = 0; i < roles.length; i++) {
					var roleVal = roles[i],roleName = ROLES[roleVal];
					shtml = shtml + '<span class="label label-info pointer">'+roleName+'</span> ';
				}
				return shtml;
			}
		}
	},
	{
		name: 'create_time',
		label: '创建时间',
		width: 0.2,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return dataValue == null || dataValue == 'null' ? '' : dataValue;
			}
		}
	},
	{
		name: 'option',
		label: '操作',
		width: 65,
		sort: false,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				var row = dataGrid.getDataItem(cell.rowIndex - 1),
					uid = row['id'],
					editUrl = getBaseUrl('auth/eidt/' + uid),
					suid = $("#session-uid").val();
				return uid == suid ? '' : '<a  href="' + editUrl + '" title="更新"><i class="icon icon-pencil"></i></a>';
			}
		}
	}
];
///////////////////////////////////////
(function() {
	$('#datagridLog').datagrid({
		height: 'page',
		dataSource: {
			cols: auth_cols,
			remote: function(params, datagrid) {
				var urlBase = getBaseUrl('api/auth/search');
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
			search: $('#inputSearchUserCfg').val()
		},
		//			   hoverCell:true,
		renderDelay: 800,
		sortable: true,
		autoOnSearchChange: true
	});

})();