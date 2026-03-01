const skillLogService = (() => {
    const getList = async (page, {type, keyword, tagNames}, callback) => {
        page = page || 1;

        let queryString = `?type=${type}`;
        queryString += `&keyword=${keyword}`
        if(tagNames){
            tagNames.forEach((tagName) => {
                queryString += `&tagNames=${tagName}`
            });
        }

        const response = await fetch(`/api/skill-log/list/${page}${queryString}`)
        const skillLogWithPaging = await response.json();
        if(callback){
            callback(skillLogWithPaging, queryString);
        }
    }

    return {getList: getList};
})();