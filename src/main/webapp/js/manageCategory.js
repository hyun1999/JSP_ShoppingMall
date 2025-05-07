document.addEventListener("DOMContentLoaded", () => {
    renderLevel1();

    document.querySelectorAll(".edit-btn").forEach(button => {
        button.addEventListener("click", () => {
            const id = button.dataset.id;
            const name = button.dataset.name;
            const desc = button.dataset.description;
            const parentId = parseInt(button.dataset.parent);

            document.getElementById("name").value = name;
            document.getElementById("description").value = desc;
            document.getElementById("categoryId").value = id;
            document.getElementById("actionInput").value = "update";

            // 초기화
            document.getElementById("level1").value = "";
            document.getElementById("level2").innerHTML = '<option value="">2차 카테고리 선택</option>';

            if (parentId !== 0) {
                const parent = allCategories.find(c => c.id === parentId);
                if (parent) {
                    const grandParent = allCategories.find(c => c.id === parent.parentId);
                    if (grandParent) {
                        document.getElementById("level1").value = grandParent.id;
                        renderLevel2();
                        document.getElementById("level2").value = parent.id;
                    } else {
                        document.getElementById("level1").value = parent.id;
                        renderLevel2();
                    }
                }
            }
        });
    });
});

function renderLevel1() {
    const level1 = document.getElementById("level1");
    level1.innerHTML = '<option value="">1차 카테고리 선택</option>';
    allCategories.filter(c => c.parentId === 0).forEach(cat => {
        level1.add(new Option(cat.name, cat.id));
    });
}

function renderLevel2() {
    const level1Id = parseInt(document.getElementById("level1").value);
    const level2 = document.getElementById("level2");
    level2.innerHTML = '<option value="">2차 카테고리 선택</option>';

    if (isNaN(level1Id)) return;

    allCategories.filter(c => c.parentId === level1Id).forEach(cat => {
        level2.add(new Option(cat.name, cat.id));
    });
}

// 계층별 parentId 설정
function setCorrectParentId(event) {
    const level2 = document.getElementById("level2").value;
    const level1 = document.getElementById("level1").value;

    let parentId = 0;
    if (level2) {
        parentId = level2;
    } else if (level1) {
        parentId = level1;
    }

    document.getElementById("parentId").value = parentId;
}
