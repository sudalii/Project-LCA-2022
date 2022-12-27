function addRow() {
    var id = document.getElementById('table-handling');
    // var row = my_tbody.insertRow(0); // 상단에 추가
    var row = id.insertRow( id.rows.length ); // 하단에 추가
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    //cell1.innerHTML = 'HELLO world';
    //cell2.innerHTML = new Date().toUTCString();
  }

  function deleteRow() {
    var id = document.getElementById('table-handling');
    if (id.rows.length < 1) return;
    // my_tbody.deleteRow(0); // 상단부터 삭제
    id.deleteRow( id.rows.length-1 ); // 하단부터 삭제
  }