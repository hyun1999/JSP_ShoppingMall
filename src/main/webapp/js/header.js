document.addEventListener("DOMContentLoaded", () => {
    const arrow = document.getElementById("arrow-icon");
    const dropdown = document.getElementById("dropdown-menu");
    const selectWrapper = document.querySelector(".select-wrapper");
    const selected = document.getElementById("selected-category");
    const hiddenInput = document.getElementById("category-input");

    let isDropdownOpen = false;

    if (!arrow || !dropdown || !selectWrapper || !selected || !hiddenInput) return;

    // 드롭다운 토글
    selectWrapper.addEventListener("click", function (e) {
        isDropdownOpen = !isDropdownOpen;
        console.log("comeIn");
        console.log("열려있는가 : "+isDropdownOpen)
        dropdown.style.display = isDropdownOpen ? "block" : "none";
        arrow.src = isDropdownOpen
            ? contextPath + "/images/upArrow.png"
            : contextPath + "/images/downArrow.png";
    });

    // 항목 선택
    document.querySelectorAll(".dropdown-item").forEach(item => {
        item.addEventListener("click", function (e) {
            const text = this.textContent;
            const value = this.dataset.value;

            selected.textContent = text;
            hiddenInput.value = value;

            isDropdownOpen = false;
            dropdown.style.display = "none";
            arrow.src = contextPath + "/images/downArrow.png";

            e.stopPropagation();
        });
    });

    // 외부 클릭 시 드롭다운 닫기
    document.addEventListener("click", (e) => {
        if (!selectWrapper.contains(e.target)) {
            dropdown.style.display = "none";
            arrow.src = contextPath + "/images/downArrow.png";
            isDropdownOpen = false;
        }
    });
});
