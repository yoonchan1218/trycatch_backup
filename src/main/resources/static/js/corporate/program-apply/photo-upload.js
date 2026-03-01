// 사진 업로드
(function () {
    const photoUploadBox = document.getElementById('photoUploadBox');
    const photoInput = document.getElementById('photoInput');
    const photoPreviewList = document.getElementById('photoPreviewList');

    if (!photoUploadBox || !photoInput || !photoPreviewList) return;

    // 파일 누적 관리용 DataTransfer
    const dataTransfer = new DataTransfer();

    photoUploadBox.addEventListener('click', function () {
        photoInput.click();
    });

    photoUploadBox.addEventListener('dragover', function (e) {
        e.preventDefault();
        photoUploadBox.classList.add('dragover');
    });

    photoUploadBox.addEventListener('dragleave', function () {
        photoUploadBox.classList.remove('dragover');
    });

    photoUploadBox.addEventListener('drop', function (e) {
        e.preventDefault();
        photoUploadBox.classList.remove('dragover');
        handleFiles(e.dataTransfer.files);
    });

    photoInput.addEventListener('change', function (e) {
        handleFiles(e.target.files);
    });

    function handleFiles(files) {
        Array.from(files).forEach(function (file) {
            if (!file.type.startsWith('image/')) return;
            if (file.size > 5 * 1024 * 1024) {
                alert('파일 크기는 5MB 이하만 가능합니다.');
                return;
            }
            if (dataTransfer.files.length >= 5) {
                alert('최대 5장까지 업로드 가능합니다.');
                return;
            }

            // DataTransfer에 파일 추가
            dataTransfer.items.add(file);
            const fileIndex = dataTransfer.files.length - 1;

            var reader = new FileReader();
            reader.onload = function (e) {
                var item = document.createElement('div');
                item.className = 'photo-preview-item';
                item.dataset.fileIndex = fileIndex;
                item.innerHTML =
                    '<img src="' + e.target.result + '" alt="미리보기">' +
                    '<button type="button" class="photo-remove-btn">' +
                    '<svg width="16" height="16" viewBox="0 0 24 24" fill="white"><path d="M18 6L6 18M6 6l12 12" stroke="white" stroke-width="2" stroke-linecap="round"/></svg>' +
                    '</button>';

                item.querySelector('.photo-remove-btn').addEventListener('click', function () {
                    removeFile(item);
                });

                photoPreviewList.appendChild(item);
            };
            reader.readAsDataURL(file);
        });

        // input의 files를 DataTransfer로 동기화
        photoInput.files = dataTransfer.files;
    }

    function removeFile(item) {
        // 현재 남아있는 미리보기 목록에서 인덱스 찾기
        const items = Array.from(photoPreviewList.children);
        const index = items.indexOf(item);

        // DataTransfer에서 해당 파일 제거
        dataTransfer.items.remove(index);

        // input 동기화
        photoInput.files = dataTransfer.files;

        // 미리보기 제거
        item.remove();
    }
})();
