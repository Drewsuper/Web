export function isvalidateUsername(str){
    const reg=/^(?![^A-Za-z]+$)(?![^0-9A-Za-z]+$)[0-9A-Za-z]{4,18}$/;
    return reg.test(str)
}
export function isvalidatePassword(str){
    const reg= new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');
    return reg.test(str)
}
