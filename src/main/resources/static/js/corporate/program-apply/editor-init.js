// CKEditor 5 초기화
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

        const templateHTML = `
<div id="dev-template-v2-root" class="tempate-detailed-summary-root pc dev-case-type case1" templateno="11">

    <div class="detailed-summary-contents">
        <div class="detailed-summary-content" id="dev-template-v2-part">
            <table class="content-table">
                <tbody>
                    <tr>
                        <td>
                            <p class="heading">
                                <i class="icon-header dev-case-type case1"></i>
                                포지션 및 자격요건
                            </p>
                        </td>
                    </tr>
                </tbody>
            </table>

            <div class="recruitment">
                <table class="__se_tbl __se_tbl_noresize dev-list-type">
                    <tbody>
                        <tr>
                            <td class="header">
                                <p><b>포지션 </b><br>( 1명 )</p>
                            </td>
                            <td>
                                <table class="content-table">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <p><b>담당업무</b></p>
                                                <p>ㆍ상세내용을 입력하세요</p>
                                                <p>ㆍ상세내용을 입력하세요</p>
                                                <p><br></p>

                                                <p><b>자격요건</b></p>
                                                <p>ㆍ학력 : 무관</p>
                                                <p>ㆍ나이 : 무관</p>
                                                <p><br></p>

                                                <p><b>우대사항</b></p>
                                                <p>ㆍ상세내용을 입력하세요</p>
                                                <p>ㆍ상세내용을 입력하세요</p>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <p><br></p>

        <div class="detailed-summary-content">
            <table class="content-table">
                <tbody>
                    <tr>
                        <td>
                            <p class="heading">
                                <i class="icon-header dev-case-type case1"></i>
                                전형절차
                            </p>
                            <p>ㆍ선착순 모집 후 체험.</p>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <p><br></p>

        <div class="detailed-summary-content">
            <table class="content-table">
                <tbody>
                    <tr>
                        <td>
                            <p class="heading">
                                <i class="icon-header dev-case-type case1"></i>
                                유의사항
                            </p>
                            <p>ㆍ허위사실이 발견될 경우 공고등록이 취소될 수 있습니다.</p>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
        `;

        editor.setData(templateHTML);

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
