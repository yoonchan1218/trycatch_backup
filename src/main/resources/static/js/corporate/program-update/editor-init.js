// CKEditor 5 초기화 (수정 페이지용 - 기존 데이터 로드)
const {
    ClassicEditor,
    Essentials,
    Paragraph,
    Bold,
    Italic,
    Font
} = CKEDITOR;

ClassicEditor
    .create(document.querySelector('#editor'), {
        licenseKey: 'eyJhbGciOiJFUzI1NiJ9.eyJleHAiOjE4MDE0Mzk5OTksImp0aSI6IjQ5NDc4MWFjLWYxNDEtNGMxZC1iN2I1LTliODRlMDRhYTUzZCIsInVzYWdlRW5kcG9pbnQiOiJodHRwczovL3Byb3h5LWV2ZW50LmNrZWRpdG9yLmNvbSIsImRpc3RyaWJ1dGlvbkNoYW5uZWwiOlsiY2xvdWQiLCJkcnVwYWwiXSwiZmVhdHVyZXMiOlsiRFJVUCIsIkUyUCIsIkUyVyJdLCJyZW1vdmVGZWF0dXJlcyI6WyJQQiIsIlJGIiwiU0NIIiwiVENQIiwiVEwiLCJUQ1IiLCJJUiIsIlNVQSIsIkI2NEEiLCJMUCIsIkhFIiwiUkVEIiwiUEZPIiwiV0MiLCJGQVIiLCJCS00iLCJGUEgiLCJNUkUiXSwidmMiOiIzNWZhZWJiYSJ9.GUuadyumc2UZdayx4LvIixjahvWxApx4FULvl66FYJuPb8tQf-YAEgoN8GWPdY40horW2G_Tw5PKJFpZwzoe0Q',
        plugins: [Essentials, Paragraph, Bold, Italic, Font],
        toolbar: [
            'undo', 'redo', '|', 'bold', 'italic', '|',
            'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor'
        ]
    })
    .then(editor => {
        // 기존 데이터가 있으면 로드, 없으면 빈 상태
        if (typeof programDescription !== 'undefined' && programDescription) {
            editor.setData(programDescription);
        }

        // 폼 제출 시 에디터 데이터, 근무요일, 근무시간, 주소를 동기화
        document.getElementById('recruitForm').addEventListener('submit', function () {
            document.querySelector('textarea[name="experienceProgramDescription"]').value = editor.getData();

            var wds = document.getElementById('workday-start').value;
            var wde = document.getElementById('workday-end').value;
            document.getElementById('workDaysHidden').value = wds + '~' + wde;

            var sh = document.getElementById('workStartHour').value;
            var sm = document.getElementById('workStartMin').value;
            var eh = document.getElementById('workEndHour').value;
            var em = document.getElementById('workEndMin').value;
            document.getElementById('workHoursHidden').value = sh + ':' + sm + '~' + eh + ':' + em;

            // 상세주소 hidden 동기화
            var detailAddr = document.getElementById('detailAddress');
            if (detailAddr) {
                document.getElementById('addressDetailHidden').value = detailAddr.value;
            }
        });
    })
    .catch(error => {
        console.error(error);
    });
