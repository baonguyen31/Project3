<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingURLValue" value="/admin/building-list"/>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<c:url var="buildingAPI" value="/api/building"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> Danh sách tòa nhà </head>
<body>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>
							<li class="active">Dasboard</li>
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">

						<div class="page-header">
							<h1>
								Dashboard
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									overview &amp; stats
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
										<div class="widget-box ui-sortable-handle">
											<div class="widget-header">
												<h5 class="widget-title">Tìm kiếm</h5>

												<div class="widget-toolbar">
													<div class="widget-menu">

													<a href="#" data-action="collapse">
														<i class="ace-icon fa fa-chevron-up"></i>
													</a>

													</div>
												</div>
											</div>

											<div class="widget-body" styple="font-family: Times New Roman">
												<div class="widget-main">
												<form:form modelAttribute="modelSearch" id="listForm" action="${buildingURLValue}" method="GET">
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <div class="col-xs-12">
                                                                <div class="col-xs-6">
                                                                    <label class="name"> Tên tòa nhà </label>
                                                                    <input type="text" class="form-control" name="name" value="${modelSearch.name}"/>
                                                                </div>
                                                                <div class="col-xs-6">
                                                                    <label class="name">Diện tích sàn</label>
                                                                    <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="col-xs-12">
                                                                <div class="col-xs-2">
                                                                    <label class="name"> Quận </label>
                                                                    <form:select class="form-control" path="district">
                                                                        <form:option value="">---Chọn Quận---</form:option>
                                                                        <form:options items="${districtList}"></form:options>
                                                                    </form:select>
                                                                </div>
                                                                <div class="col-xs-5">
                                                                    <label class="name"> Phường  </label>
                                                                    <form:input class="form-control" path="ward"/>
                                                                </div>
                                                                <div class="col-xs-5">
                                                                    <label class="name"> Đường </label>
                                                                    <form:input class="form-control" path="street" />
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="col-xs-12">
                                                                <div class="col-xs-4">
                                                                    <label class="name"> Số tầng hầm </label>
                                                                    <form:input type="number" class="form-control"  path="numberOfBasement" />


                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <label class="name"> Hướng </label>
                                                                    <form:input class="form-control" path="direction"/>

                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <label class="name"> Hạng </label>
                                                                    <form:input  class="form-control" path="level"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="col-xs-12">
                                                                <div class="col-xs-3">
                                                                    <label class="name">Diện tích từ</label>
                                                                    <form:input  class="form-control" path="areaFrom" type="number"/>


                                                                </div>
                                                                <div class="col-xs-3">
                                                                    <label class="name"> Diện tích đến </label>
                                                                    <form:input class="form-control" path="areaTo" type="number"/>

                                                                </div>
                                                                <div class="col-xs-3">
                                                                    <label class="name"> Giá thuê từ  </label>
                                                                    <form:input type="number" class="form-control" path="rentPriceFrom"/>
                                                                </div>

                                                                <div class="col-xs-3">
                                                                    <label class="name"> Giá thuê đến </label>
                                                                    <form:input type="number" class="form-control" path="rentPriceTo" />
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="col-xs-12">
                                                                <div class="col-xs-4">
                                                                    <label class="name"> Tên quản lý  </label>
                                                                    <input type="text" class="form-control" name="managerName" value=""/>

                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <label class="name"> Số điện thoại quản lý </label>
                                                                    <input type="text" class="form-control" name="managerPhone" value=""/>
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <label class="name"> Chọn nhân viên phụ trách </label>
                                                                    <form:select class="form-control" path="staffId">
                                                                        <form:option value="">---Chọn nhân viên---</form:option>
                                                                        <form:options items="${listStaffs}"/>

                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="col-xs-12">
                                                                <div class="col-xs-6">
                                                                    <label>
                                                                        <form:checkboxes path="typeCode" items="${typeCode}"/>
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12">
                                                            <div class="col-xs-6">
                                                                <button class="btn btn-xs btn-danger" id="buildingSearchButton">
                                                                    <i class="ace-icon fa fa-search bigger-110"></i>
                                                                    Tìm kiếm
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form:form>

											</div>
									</div>
									<div class="pull-right">

										<button class="btn btn-success" title="Thêm tòa nhà" >
											<a href= "${buildingEditURL}" style="color: white;">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
										<path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
										<path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
										<path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
										</svg>
										</a>
										</button>

										<button class="btn btn-danger" title="Xóa tòa nhà" id="btnDeleteBuilding">

										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-fill-x" viewBox="0 0 16 16">
										<path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
										<path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m-.646-4.854.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 0 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 .708-.708"/>
										</svg>

										</button>

										</div>
								</div>
							</div>
						</div><!-- /.col -->
						</div><!-- /.row -->
						<!-- Bảng danh sách tòa nhà -->
						<div class="row"  style="margin: 3em 0 1.5em;">
						 <div class="col-xs-12">
										<table id="tableList" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">
														<label class="pos-rel">
															<input type="checkbox" class="ace">
															<span class="lbl"></span>
														</label>
													</th>
													<th>Tên tòa nhà</th>
													<th>Địa chỉ</th>
													<th>Số tầng hầm</th>
													<th>Tên quản lý</th>
													<th>Số điện thoại</th>
													<th>Diện tích sàn</th>
													<th>Diện tích trống</th>
													<th>Diện tích thuê</th>
													<th>Phí môi giới</th>
													<th >Thao tác</th>

												</tr>
											</thead>

											<tbody>
											<c:forEach var="item" items="${buildingList}">

												<tr>
													<td class="center">
														<label class="pos-rel">
															<input type="checkbox" class="ace" name="checkList" value="${item.id}">
															<span class="lbl"></span>
														</label>
													</td>

													<td>${item.name}</td>
													<td>${item.address}</td>
													<td>${item.numberOfBasement}</td>
													<td>${item.managerName}</td>
													<td>${item.managerPhone }</td>
													<td>${item.floorArea}</td>
													<td>${item.emptyArea}</td>
													<td>${item.rentArea}</td>
													<td>${item.brokerageFee}</td>

                                                    <td><sp></sp></td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<button class="btn btn-xs btn-success" title="Giao tòa nhà" onclick="assignmentBuilding(${item.id})">
																<i class="ace-icon glyphicon glyphicon-list"></i>
															</button>

															<a class="btn btn-xs btn-info" title="Sửa tòa nhà" href="${buildingEditURL}-${item.id}">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</a>

															<button class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteBuilding(${item.id})">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
                                                        </div>
												</tr>

                                               </c:forEach>
											</tbody>
										</table>
									</div>

						</div><!-- /.row -->
						<!-- Kết thúc bảng danh sách tòa nhà -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		   <div class="modal fade" id="assignmentBuildingModel" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
                <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" ></button>
                      <h4 class="modal-title">Danh sách nhân viên</h4>
                    </div>
                    <div class="modal-body">
                    	<table class="table table-striped table-bordered table-hover" id="staffList">
            											<thead>
            												<tr>
            													<th class="center">
            													Chọn
            													</th >
            													<th  class="center">Tên nhân viên</th>
            												</tr>
            											</thead>

            											<tbody class="center">
<%--            												<tr>--%>
<%--            													<td>--%>
<%--            														<label class="pos-rel">--%>
<%--            															<input type="checkbox" value="1">--%>
<%--            														</label>--%>
<%--            													</td>--%>
<%--            													<td >--%>
<%--            													Nguyễn Văn A--%>
<%--            													</td>--%>
<%--            												</tr>--%>
<%--            												<tr>--%>
<%--            													<td>--%>
<%--            														<label class="pos-rel">--%>
<%--            															<input type="checkbox" value="2">--%>
<%--            														</label>--%>
<%--            													</td>--%>
<%--            													<td>--%>
<%--            													Nguyễn Văn C--%>
<%--            													</td>--%>
<%--            												</tr>--%>
													</tbody>
            								</table>
            								<input type="hidden" id="buildingId" value=""/>
                    </div>
                    <div class="modal-footer">
            			<button type="button" class="btn btn-default" id="assignBuilding">Giao nhân viên</button>
                      <button type="button" class="btn btn-default" onclick= "closeButton()" >Close</button>
                    </div>
                  </div>

                </div>
              </div>
    <script>
      function assignmentBuilding(buildingId){
         $('#assignmentBuildingModel').modal();
		 assignBuilding(buildingId);
		 $('#buildingId').val(buildingId)


        }
        $('#assignBuilding').click(function(e){
				e.preventDefault();
				var data = {};
				data['buildingId'] = $('#buildingId').val();
				var staffIds = $('#staffList').find('tbody input[type="checkbox"]:checked').map(function(){
					return $(this).val();
				}).get();
				data['staffIds'] = staffIds;
				console.log(data);

				$.ajax({
				type: "POST",
						url: "${buildingAPI}/"+ data['buildingId'] + "/staffs",
						data: JSON.stringify(staffIds),
						contentType: "application/json",
				 		success: function(respond){
							console.log("thanh cong!");
							window.location.href = "/admin/building-list"
					},
						error: function(respond){
							console.log("Lỗi rồi!");
						}
						})
					});

		function assignBuilding(buildingId){
			$.ajax({
				type: "Get",
						url: "${buildingAPI}/"+ buildingId + "/staffs",
						// data: JSON.stringify(data),
						contentType: "application/json",
				 		success: function(respond){
								var row = '';
								$.each(respond.data, function(index, item){
									row += '<tr>';
									row += '<td class ="text-center" ><input type="checkbox" value="' + item.staffId + '" id="checkbox_'+  item.staffId + '" ' + item.checked + '></td>';
									row += '<td class="text-centerr">' + item.fullName + '</td>';
									row +='</tr>';

								});
							$('#staffList tbody').html(row)
							console.log("thanh cong!");
						},
						error: function(respond){
							console.log("Lỗi rồi!");
						}
						});
	 			 }

        $('#buildingSearchButton').click(function(e){
            e.preventDefault();
            $('#listForm').submit();
        });
	  function deleteBuilding(data){
	  	var buildingId = [data]
	  	deleteBuildings(buildingId)
	  }
	  $('#btnDeleteBuilding').click(function(e){
	 		 e.preventDefault();
				var data = {};
				data['buildingId'] = $('#buildingId').val();
				var buildingId = $('#tableList').find('tbody input[type="checkbox"]:checked').map(function(){
					return $(this).val();
				}).get();
  				deleteBuildings(buildingId)
	  });
		function deleteBuildings(data){
			$.ajax({
				type: "Delete",
						url: "${buildingAPI}/"+ data,
						data: JSON.stringify(data),
						contentType: "application/json",
				 		success: function(respond){
							console.log("thanh cong!");
							window.location.href = "<c:url value="/admin/building-list"/>";
					},
						error: function(respond){
							console.log("Lỗi rồi!");
						}
						});
	  }
	  function closeButton(){
	  	$('#assignmentBuildingModel').modal('hide');
		}


    </script>

</body>
</html>
