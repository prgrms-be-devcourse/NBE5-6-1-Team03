<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>

<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main>
    <div class="container">
        <form:form modelAttribute="orderForm" action="${context}/order" method="post" class="col s12">
            <div class="menu-list container">
                <h4 class="brown-text text-darken-3 center-align">메뉴</h4>
                <div class="row">
                    <c:set var="imageList" value="${imageMap}"/>
                    <table class="highlight centered">
                        <thead>
                        <tr>
                            <th>     </th>
                            <th>메뉴명</th>
                            <th>가격</th>
                            <th>재고</th>
                            <th>추가</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="menu" items="${menus}">
                            <tr id="menu-${menu.id}"
                                data-id="${menu.id}"
                                data-name="${menu.name}"
                                data-price="${menu.price}">
                                <td>
                                    <c:if test="${empty imageMap[menu.id][0]}">
                                        <img src="/assets/img/sample.jpeg" alt="샘플" style="width: 60px; height: 60px; object-fit: cover;">
                                    </c:if>
                                    <c:if test="${not empty imageMap[menu.id][0]}">
                                        <img src="${imageMap[menu.id][0].renamedUrl}" alt="${menu.name}" style="width: 60px; height: 60px; object-fit: cover;">
                                    </c:if>
                                </td>
                                <td>${menu.name}</td>
                                <td>${menu.price}원</td>
                                <td>${menu.amount}개</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${menu.amount > 0}">
                                            <button type="button"
                                                    class="btn brown darken-2 waves-effect waves-light"
                                                    onclick="addToOrder(this)">
                                                추가
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            sold out
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="order-summary container">
                <h4 class="brown-text text-darken-3 center-align">주문 요약</h4>

                <!-- 주문 테이블 카드 -->
                <div class="card">
                    <div class="card-content">
                        <table class="striped centered responsive-table" id="orderTable">
                            <thead>
                            <tr>
                                <th>메뉴</th>
                                <th>수량</th>
                                <th>가격</th>
                                <th>조작</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="oderedMenu" items="${orderedMenus}" varStatus="status">
                                <tr data-id="${oderedMenu.id}">
                                    <td>
                                            ${oderedMenu.name}
                                        <form:input type="hidden" path="menuList[${status.index}].id" value="${oderedMenu.id}" cssClass="input-id"/>
                                        <form:input type="hidden" path="menuList[${status.index}].name" value="${oderedMenu.name}" cssClass="input-name"/>
                                        <form:input type="hidden" path="menuList[${status.index}].price" value="${oderedMenu.price}" cssClass="input-price"/>
                                    </td>
                                    <td>
                                        <form:input type="number" path="menuList[${status.index}].quantity"
                                                    value="${oderedMenu.quantity}" cssClass="quantity-input" readonly="true"/>
                                    </td>
                                    <td class="total-price">${oderedMenu.price * oderedMenu.quantity}</td>
                                    <td>
                                        <button type="button" class="btn-small increase">+</button>
                                        <button type="button" class="btn-small decrease">-</button>
                                        <button type="button" class="btn-small red remove">삭제</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- 주문자 정보 입력 -->
                <div class="card">
                    <div class="card-content">
                        <h5 class="brown-text text-darken-2">주문자 정보</h5>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <form:input type="hidden" path="userId" id="userId" value="${empty member.id ? null : member.id}" />

                                <form:input path="userEmail" id="userEmail" type="email" class="validate" value="${member.email}" />
                                <label for="userEmail">이메일</label>
                                <form:errors path="userEmail" cssClass="helper-text red-text text-darken-2" />
                            </div>
                            <div class="input-field col s12 m6">
                                <form:input path="userAddress" id="userAddress" type="text" class="validate" value="${member.address}" />
                                <label for="userAddress">주소</label>
                                <form:errors path="userAddress" cssClass="helper-text red-text text-darken-2" />
                            </div>
                        </div>
                        <div class="row center-align">
                            <button type="submit" class="btn-large brown darken-2 waves-effect waves-light">
                                주문하기
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

<script>
  function addToOrder(button) {
    const cardContent = button.closest('tr');
    const id = String(cardContent.dataset.id);
    const name = cardContent.dataset.name;
    const price = parseInt(cardContent.dataset.price);

    const tbody = document.querySelector('#orderTable tbody');

    // 이미 존재하는 항목인지 확인
    let existingRow = tbody.querySelector(`tr[data-id="\${id}"]`);

    if (existingRow) {
      const quantityInput = existingRow.querySelector('.quantity-input');
      const totalPriceCell = existingRow.querySelector('.total-price');

      let quantity = parseInt(quantityInput.value);
      quantityInput.value = ++quantity;
      totalPriceCell.textContent = quantity * price;
    } else {
      const tr = document.createElement('tr');
      tr.setAttribute('data-id', id);

      tr.innerHTML = `
        <td>
          \${name}
          <input type="hidden" class="input-name" name="menuList[0].name" value="\${name}" />
          <input type="hidden" class="input-id" name="menuList[0].id" value="\${id}" />
          <input type="hidden" class="input-price" name="menuList[0].price" value="\${price}" />
        </td>
        <td>
          <input type="number" class="quantity-input" name="menuList[0].quantity" value="1" min="1" readonly />
        </td>
        <td class="total-price">\${price}</td>
        <td>
          <button type="button" class="btn-small increase">+</button>
          <button type="button" class="btn-small decrease">-</button>
          <button type="button" class="btn-small red remove">삭제</button>
        </td>
      `;

      // 이벤트 바인딩
      tr.querySelector('.increase').addEventListener('click', () => {
        const quantityInput = tr.querySelector('.quantity-input');
        const totalPriceCell = tr.querySelector('.total-price');
        let quantity = parseInt(quantityInput.value);
        quantityInput.value = ++quantity;
        totalPriceCell.textContent = quantity * price;
      });

      tr.querySelector('.decrease').addEventListener('click', () => {
        const quantityInput = tr.querySelector('.quantity-input');
        const totalPriceCell = tr.querySelector('.total-price');
        let quantity = parseInt(quantityInput.value);
        if (quantity > 1) {
          quantityInput.value = --quantity;
          totalPriceCell.textContent = quantity * price;
        }
      });

      tr.querySelector('.remove').addEventListener('click', () => {
        tr.remove();
        updateIndexes(); // 인덱스 재정렬
      });

      tbody.appendChild(tr);
      updateIndexes(); // 새로 추가했으니 인덱스 재정렬
    }
  }

  function updateIndexes() {
    const rows = document.querySelectorAll('#orderTable tbody tr');
    rows.forEach((tr, index) => {
      tr.querySelector('.input-id').setAttribute('name', `menuList[\${index}].id`);
      tr.querySelector('.input-name').setAttribute('name', `menuList[\${index}].name`);
      tr.querySelector('.input-price').setAttribute('name', `menuList[\${index}].price`);
      tr.querySelector('.quantity-input').setAttribute('name', `menuList[\${index}].quantity`);
    });
  }
</script>



</html>
