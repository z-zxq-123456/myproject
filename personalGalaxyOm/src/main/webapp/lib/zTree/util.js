

Date.prototype.format = function(format) {
             var date = {
                    "M+": this.getMonth() + 1,
                    "d+": this.getDate(),
                    "h+": zeroize(this.getHours()),
                    "m+": zeroize(this.getMinutes()),
                    "s+": zeroize(this.getSeconds()),
                    "q+": Math.floor((this.getMonth() + 3) / 3),
                    "S+": this.getMilliseconds()
             };
             if (/(y+)/i.test(format)) {
                    format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
             }
             for (var k in date) {
                    if (new RegExp("(" + k + ")").test(format)) {
                           format = format.replace(RegExp.$1, RegExp.$1.length == 1
                                  ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
                    }
             }
             return format;
      }


var zeroize = function (value, length) {

    if (!length) length = 2;

    value = String(value);

    for (var i = 0, zeros = ''; i < (length - value.length); i++) {

        zeros += '0';

    }

    return zeros + value;

};

function getDateStr(time, format) {
	if (time != null && time != '' && time != 0) {
		var date = new Date();
		date.setTime(time);
		return date.format(format);
	} 
	return "";
}
