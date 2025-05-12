document.addEventListener("DOMContentLoaded", function () {
    const selectAllCheckbox = document.querySelector('input[type="checkbox"][onclick*="toggleSelectAll"]');
    const itemCheckboxes = document.querySelectorAll('input[name="selectedItems"]');
    const selectAllLabel = document.querySelector(".select-all-label");
    const countSpan = document.createElement("span");
    const totalAmountSpan = document.getElementById("totalAmount");
    const cartForm = document.querySelector('form[action="orderSelected.do"]');

    countSpan.style.fontSize = "12px";
    countSpan.style.color = "#666";
    countSpan.style.marginTop = "4px";
    selectAllLabel.appendChild(countSpan);

    function updateSummary() {
        const selectedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
        countSpan.textContent = `선택됨: ${selectedItems.length}개`;
        selectAllCheckbox.checked = selectedItems.length === itemCheckboxes.length;

        let total = 0;
        selectedItems.forEach(cb => {
            const row = cb.closest("tr");
            const amountCell = row.querySelector("td[data-amount]");
            if (amountCell) {
                const raw = amountCell.dataset.amount;
                total += parseInt(raw, 10);
            }
        });

        totalAmountSpan.textContent = `총 금액: ₩${total.toLocaleString()}`;
    }

    window.toggleSelectAll = function (source) {
        itemCheckboxes.forEach(cb => cb.checked = source.checked);
        updateSummary();
    };

    itemCheckboxes.forEach(cb => {
        cb.addEventListener("change", updateSummary);
    });

    cartForm.addEventListener("submit", function (e) {
        const selectedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
        const submitBtn = e.submitter;

        if (submitBtn && submitBtn.getAttribute("formaction") === "orderSelected.do" && selectedItems.length === 0) {
            alert("주문할 상품을 하나 이상 선택해주세요.");
            e.preventDefault();
        }
    });

    updateSummary();
});
