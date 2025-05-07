function confirmDelete() {
    return confirm("정말로 삭제하시겠습니까?");
}

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("productForm");
    if (form) {
        form.addEventListener("submit", function (e) {
            const productName = form.nmProduct.value.trim();
            const salePrice = form.qtSalePrice.value.trim();

            if (!productName || !salePrice) {
                alert("상품명과 판매 가격은 필수입니다.");
                e.preventDefault();
            }
        });
    }
});
