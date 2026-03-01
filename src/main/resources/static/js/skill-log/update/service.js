const skillLogService = (() => {
    const getRecentExperienceProgramLogs = async (page, id, keyword, callback) => {
        const response = await fetch(`/api/skill-log/experience-program-logs/${page}?id=${id}&keyword=${keyword}`);
        const experienceProgramLogsWithPaging = await response.json();
        if(callback) {
            return callback(experienceProgramLogsWithPaging);
        }
    }

    return {getRecentExperienceProgramLogs: getRecentExperienceProgramLogs};
})();