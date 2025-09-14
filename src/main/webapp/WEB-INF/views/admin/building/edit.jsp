<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<c:url var="buildingAPI" value="/api/building"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> Danh sách tòa nhà</head>
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
								<a href="#">Quản lý tòa nhà</a>
							</li>
							<li class="active">Thêm tòa nhà</li>
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">

						<div class="page-header">
							<h1>
								Quản lý tòa nhà
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Thêm tòa nhà
								</small>
							</h1>
						</div><!-- /.page-header -->
                            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                                <form:form modelAttribute="buildingEdit" action="buildingEditURL" id="listForm" method="GET">
                                <div class="col-xs-12">
                                    <!-- PAGE CONTENT BEGINS -->
                                    <form class="form-horizontal" role="form" >
                                        <div class="form-group">
                                            <label class="col-xs-3">Tên tòa nhà</label>
                                            <div class="col-xs-9">
                                                <input type="text" class="form-control" id="name" name="name" placeholder="Tên tòa nhà" value="${buildingEdit.name}" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-xs-3">Quận</label>
                                            <div class="col-xs-3">
                                                <form:select path="district" id="districtid">
                                                    <form:option value="">---Chọn Quận</form:option>
                                                    <form:options items="${districtList}"></form:options>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-xs-3">Phường</label>
                                            <div class="col-xs-9">
                                                <input type="text" class="form-control" id="ward" name="ward" placeholder="Phường"  value="${buildingEdit.ward}"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Đường</label>
                                            <div class="col-xs-9">
                                                <input type="text" class="form-control" id="street" name="street" placeholder="Đường"  value="${buildingEdit.street}"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Kết cấu</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="structure" placeholder="Kết cấu" path="structure"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Số tầng hầm</label>
                                            <div class="col-xs-9">
                                                <form:input type="number" class="form-control" id="numberOfBasement" path="numberOfBasement" placeholder="Số tầng hầm"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Diện tích sàn</label>
                                            <div class="col-xs-9">
                                                <form:input type="number" class="form-control" id="floorArea" path="floorArea" placeholder="Diện tích sàn"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Hướng</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="direction" path="direction" placeholder="Hướng"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Hạng</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="level" path="level" placeholder="Hạng"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Diện tích thuê</label>
                                            <div class="col-xs-9">
                                                <form:input class="form-control" id="rentArea" path="rentArea" placeholder="Diện tích thuê"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Giá thuê</label>
                                            <div class="col-xs-9">
                                                <form:input type="number" class="form-control" id="rentPrice" path="rentPrice" placeholder="Giá thuê"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Mô tả giá</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="rentpricedescription" path="rentPriceDescription" placeholder="Mô tả giá"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Phí dịch vụ</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="servicefee" path="serviceFee" placeholder="Phí dịch vụ"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Phí mô tô</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="motofee" path="motoFee" placeholder="Phí mô tô"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Phí ô tô</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="carfee" path="carFee" placeholder="Phí ô tô"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Phí ngoài giờ</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="overtimefee" path="overtimeFee" placeholder="Phí ngoài giờ"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Tiền điện</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="electricityfee" path="electricityFee" placeholder="Tiền điện"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Đặt cọc</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="deposit" path="deposit" placeholder="Đặt cọc"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Thanh toán</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="payment" path="payment" placeholder="Thanh toán"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Thời hạn thuê</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="renttime" path="rentTime" placeholder="Thời hạn thuê"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Thời gian trang trí</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="decorationtime" path="decorationTime" placeholder="Thời gian trang trí"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Tên quản lý</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="managername" path="managerName" placeholder="Tên quản lý"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Số điện thoại quản lý</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="managerphonenumber" path="managerPhone" placeholder="SĐT quản lý"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Phí môi giới</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="brokeragefee" path="brokerageFee" placeholder="Phí môi giới"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Loại tòa nhà</label>
                                            <div class="col-xs-9">
                                                <label>
                                                <form:checkboxes id="typeCode" path="typeCode" items="${typeCode}"/>
                                                </label>

                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Ghi chú</label>
                                            <div class="col-xs-9">
                                                <form:input type="text" class="form-control" id="note" path="note" placeholder="Ghi chú"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-3">Hình ảnh</label>
                                            <div class="col-xs-9">
                                                <form:input type="file" class="form-control" id="avatar" path="image"/>
                                            </div>
                                        </div>
                                        <form:hidden path="id" id="buildingId"/>
                                        <div class="form-group">
                                            <label class="col-xs-3"></label>
                                            <div class="col-xs-9">
                                                <c:if test="${not empty buildingEdit.id}">
                                                <button class="btn btn-info" type="button" id="AddAndUpdateBuilding">Cập nhật tòa nhà</button>
                                                <button class="btn btn-info" type="button" id="btnCancel">Hủy thao tác</button>
                                                </c:if>
                                                <c:if test="${empty buildingEdit.id}">
                                                <button class="btn btn-info" type="button" id="AddAndUpdateBuilding">Thêm tòa nhà</button>
                                                <button class="btn btn-info" type="button" id="btnCancel">Hủy thao tác</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                           </form:form>

                            </div><!-- /.row -->
                        <!-- Bảng danh sách tòa nhà -->

						<!-- Kết thúc bảng danh sách tòa nhà -->
					</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
<script>
			$('#AddAndUpdateBuilding').click(function(e){
                e.preventDefault();
				var data = {}
				var typeCode = [];
				var formArray = $('#listForm').serializeArray();
				$.each(formArray, function(i, v) {
					if(v.name != 'typeCode'){
						data["" + v.name + ""] = v.value;
					}else{
						typeCode.push(v.value);
					}

					})
                     data['typeCode'] = typeCode;
                    if(typeCode == ''){
                        window.location.href = "/admin/building-edit?typeCode=required"
                    }
                    else if(data['name'] == ''){
                    window.location.href = "/admin/building-edit?name=required"
                    }
                    else{
                     addOrEditButton(data)
                    }
			})
            function addOrEditButton(data){
                $.ajax({
						type: "POST",
						url: "${buildingAPI}",
						data: JSON.stringify(data),
						contentType: "application/json",
				 		success: function(respond){
                            alert("Thành công")
                            window.location.href = "/admin/building-list"
							console.log("thanh cong!");
					},
						error: function(){
							console.log("Lỗi rồi!");
						}
						})
            }

            $('#btnCancel').click(function (){
            window.location.href = "/admin/building-list"
            });


</script>
</body>
</html>