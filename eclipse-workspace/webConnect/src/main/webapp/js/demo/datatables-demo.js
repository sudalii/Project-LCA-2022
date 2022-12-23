// Call the dataTables jQuery plugin
$(document).ready(function() {

  $('#optional_table').DataTable({
    paging: true
  });

  // 1) 프로젝트 정보입력
  $('#product_amount').DataTable();   // 제품양

 
  // 3) 제조전단계
  $('#pre_i_material').DataTable();   // 투입물 - 원료물질, 보조물질

  // 4) 유통 데이터
  $('#distrib_data').DataTable();   // 제품유통

  // 5) 폐기단계 데이터
  $('#disuse_data').DataTable();   // 사용중폐기

  // 6) 기본
  $('#normal').DataTable({
    paging: false,
    ordering: false,
    info: false
  });

});
