//传递乘客信息
var personData = [];
//后台传递电梯信息
//电梯层数
var layer1 = 1;
var layer2 = 1;
var layer3 = 1;
var layer4 = 1;
//电梯上升信息
var elevator1Up = [];
var elevator2Up = [];
var elevator3Up = [];
var elevator4Up = [];
//电梯下降信息
var elevator1Down = [];
var elevator2Down = [];
var elevator3Down = [];
var elevator4Down = [];
//速度
var speed = 1;
//是否到最大楼层
var hasEnd1 = false;
var hasEnd2 = false;
var hasEnd3 = false;
var hasEnd4 = false;
//是否到最小楼层
var hasDown1 = false;
var hasDown2 = false;
var hasDown3 = false;
var hasDown4 = false;
//是否完成任务
var hasComplete1 = true;
var hasComplete2 = true;
var hasComplete3 = true;
var hasComplete4 = true;
$(document).ready(function () {
    //电梯状态监控
    var ListenTimer = setInterval(function () {
        if (hasComplete1 && hasComplete2 && hasComplete3 && hasComplete4 && personData != "") {
            layer1 = layer2 = layer3 = layer4 = 1;
            $("td[id^='state']").text("停止");
        }
        // console.log(hasComplete1);
        // console.log(hasComplete2);
        // console.log(hasComplete3);
        // console.log(hasComplete4);
    }, 1000);
});

//电梯移动
function elevatorMove(start, end, num, elevator) {
    var startPosition = start * 73 + 11;
    //判断电梯升降状态
    if (start < end) {
        switch (elevator) {
            case 1:
                hasComplete1 = false;
                break;
            case 2:
                hasComplete2 = false;
                break;
            case 3:
                hasComplete3 = false;
                break;
            case 4:
                hasComplete4 = false;
                break;
        }
        var moveInterval = setInterval(function () {
            startPosition += speed;
            $(".elevator" + elevator).css("top", startPosition + "px");
            if (startPosition % 73 === 0) {
                switch (elevator) {
                    case 1:
                        layer1++;
                        $("#elevator1PersonInfor1").html("");
                        var htmlStr = "<tr>\n" +
                            "            <th>登电梯层数</th>\n" +
                            "            <th>目标层数</th>\n" +
                            "            <th>重量</th>\n" +
                            "        </tr>";
                        var sumWeight = 0;
                        $.each(elevator1Up[layer1], function (index, value) {
                            htmlStr += "        <tr>\n" +
                                "            <td>" + value.now + "</td>\n" +
                                "            <td>" + value.correct + "</td>\n" +
                                "            <td>" + value.weight + "</td>\n" +
                                "        </tr>";
                            sumWeight += parseInt(value.weight);
                        });
                        $("#personNum1").text(elevator1Up[layer1].length);
                        $("#weightNum1").text(sumWeight);
                        $("#elevator1PersonInfor1").html(htmlStr);
                        $("#layer" + elevator).text(layer1);
                        break;
                    case 2:
                        layer2++;
                        $("#elevator1PersonInfor2").html("");
                        var htmlStr = "<tr>\n" +
                            "            <th>登电梯层数</th>\n" +
                            "            <th>目标层数</th>\n" +
                            "            <th>重量</th>\n" +
                            "        </tr>";
                        var sumWeight = 0;
                        $.each(elevator2Up[layer2], function (index, value) {
                            htmlStr += "        <tr>\n" +
                                "            <td>" + value.now + "</td>\n" +
                                "            <td>" + value.correct + "</td>\n" +
                                "            <td>" + value.weight + "</td>\n" +
                                "        </tr>";
                            sumWeight += parseInt(value.weight);
                        });
                        $("#personNum2").text(elevator2Up[layer2].length);
                        $("#weightNum2").text(sumWeight);
                        $("#elevator1PersonInfor2").html(htmlStr);
                        $("#layer" + elevator).text(layer2);
                        break;
                    case 3:
                        layer3++;
                        $("#elevator1PersonInfor3").html("");
                        var htmlStr = "<tr>\n" +
                            "            <th>登电梯层数</th>\n" +
                            "            <th>目标层数</th>\n" +
                            "            <th>重量</th>\n" +
                            "        </tr>";
                        var sumWeight = 0;
                        $.each(elevator3Up[layer3], function (index, value) {
                            htmlStr += "        <tr>\n" +
                                "            <td>" + value.now + "</td>\n" +
                                "            <td>" + value.correct + "</td>\n" +
                                "            <td>" + value.weight + "</td>\n" +
                                "        </tr>";
                            sumWeight += parseInt(value.weight);
                        });
                        $("#personNum3").text(elevator3Up[layer3].length);
                        $("#weightNum3").text(sumWeight);
                        $("#elevator1PersonInfor3").html(htmlStr);
                        $("#layer" + elevator).text(layer3);
                        break;
                    case 4:
                        layer4++;
                        $("#elevator1PersonInfor4").html("");
                        var htmlStr = "<tr>\n" +
                            "            <th>登电梯层数</th>\n" +
                            "            <th>目标层数</th>\n" +
                            "            <th>重量</th>\n" +
                            "        </tr>";
                        var sumWeight = 0;
                        $.each(elevator4Up[layer4], function (index, value) {
                            htmlStr += "        <tr>\n" +
                                "            <td>" + value.now + "</td>\n" +
                                "            <td>" + value.correct + "</td>\n" +
                                "            <td>" + value.weight + "</td>\n" +
                                "        </tr>";
                            sumWeight += parseInt(value.weight);
                        });
                        $("#personNum4").text(elevator4Up[layer4].length);
                        $("#weightNum4").text(sumWeight);
                        $("#elevator1PersonInfor4").html(htmlStr);
                        $("#layer" + elevator).text(layer4);
                        break;
                }
            }
            if (startPosition >= end * 73) {
                var endPosition = end * 73 + 15;
                $(".elevator" + elevator).css("top", endPosition + "px");
                switch (elevator) {
                    case 1:
                        hasEnd1 = true;
                        hasDown1 = false;
                        break;
                    case 2:
                        hasEnd2 = true;
                        hasDown2 = false;
                        break;
                    case 3:
                        hasEnd3 = true;
                        hasDown3 = false;
                        break;
                    case 4:
                        hasEnd4 = true;
                        hasDown4 = false;
                        break;
                }
                clearInterval(moveInterval);
            }
        }, 10)
    } else {
        var moveInterval = setInterval(function () {
            startPosition -= speed;
            $(".elevator" + elevator).css("top", startPosition + "px");
            if (startPosition % 73 === 0) {
                switch (elevator) {
                    case 1:
                        layer1--;
                        $("#elevator1PersonInfor1").html("");
                        var htmlStr = "<tr>\n" +
                            "            <th>登电梯层数</th>\n" +
                            "            <th>目标层数</th>\n" +
                            "            <th>重量</th>\n" +
                            "        </tr>";
                        var sumWeight = 0;
                        $.each(elevator1Down[elevator1Down.length - layer1], function (index, value) {
                            htmlStr += "        <tr>\n" +
                                "            <td>" + value.now + "</td>\n" +
                                "            <td>" + value.correct + "</td>\n" +
                                "            <td>" + value.weight + "</td>\n" +
                                "        </tr>";
                            sumWeight += parseInt(value.weight);
                        });
                        if (elevator1Down[elevator1Down.length - layer1] != undefined)
                            $("#personNum1").text(elevator1Down[elevator1Down.length - layer1].length);
                        $("#weightNum1").text(sumWeight);
                        $("#elevator1PersonInfor1").html(htmlStr);
                        $("#layer" + elevator).text(layer1);
                        break;
                    case 2:
                        layer2--;
                        $("#elevator1PersonInfor2").html("");
                        var htmlStr = "<tr>\n" +
                            "            <th>登电梯层数</th>\n" +
                            "            <th>目标层数</th>\n" +
                            "            <th>重量</th>\n" +
                            "        </tr>";
                        var sumWeight = 0;
                        $.each(elevator2Down[elevator1Down.length - layer2], function (index, value) {
                            htmlStr += "        <tr>\n" +
                                "            <td>" + value.now + "</td>\n" +
                                "            <td>" + value.correct + "</td>\n" +
                                "            <td>" + value.weight + "</td>\n" +
                                "        </tr>";
                            sumWeight += parseInt(value.weight);
                        });
                        if (elevator2Down[elevator2Down.length - layer2] != undefined)
                            $("#personNum2").text(elevator2Down[elevator2Down.length - layer2].length);
                        $("#weightNum2").text(sumWeight);
                        $("#elevator1PersonInfor2").html(htmlStr);
                        $("#layer" + elevator).text(layer2);
                        break;
                    case 3:
                        layer3--;
                        $("#elevator1PersonInfor3").html("");
                        var htmlStr = "<tr>\n" +
                            "            <th>登电梯层数</th>\n" +
                            "            <th>目标层数</th>\n" +
                            "            <th>重量</th>\n" +
                            "        </tr>";
                        var sumWeight = 0;
                        $.each(elevator3Down[elevator3Down.length - layer3], function (index, value) {
                            htmlStr += "        <tr>\n" +
                                "            <td>" + value.now + "</td>\n" +
                                "            <td>" + value.correct + "</td>\n" +
                                "            <td>" + value.weight + "</td>\n" +
                                "        </tr>";
                            sumWeight += parseInt(value.weight);
                        });
                        if (elevator3Down[elevator3Down.length - layer3] != undefined)
                            $("#personNum3").text(elevator3Down[elevator3Down.length - layer3].length);
                        $("#weightNum3").text(sumWeight);
                        $("#elevator1PersonInfor3").html(htmlStr);
                        $("#layer" + elevator).text(layer3);
                        break;
                    case 4:
                        layer4--;
                        $("#elevator1PersonInfor4").html("");
                        var htmlStr = "<tr>\n" +
                            "            <th>登电梯层数</th>\n" +
                            "            <th>目标层数</th>\n" +
                            "            <th>重量</th>\n" +
                            "        </tr>";
                        var sumWeight = 0;
                        $.each(elevator4Down[elevator4Down.length - layer4], function (index, value) {
                            htmlStr += "        <tr>\n" +
                                "            <td>" + value.now + "</td>\n" +
                                "            <td>" + value.correct + "</td>\n" +
                                "            <td>" + value.weight + "</td>\n" +
                                "        </tr>";
                            sumWeight += parseInt(value.weight);
                        });
                        if (elevator4Down[elevator4Down.length - layer4] != undefined)
                            $("#personNum4").text(elevator4Down[elevator4Down.length - layer4].length);
                        $("#weightNum4").text(sumWeight);
                        $("#elevator1PersonInfor4").html(htmlStr);
                        $("#layer" + elevator).text(layer4);
                        break;
                }
            }
            if (startPosition <= end * 73) {
                var endPosition = end * 73 + 15;
                $(".elevator" + elevator).css("top", endPosition + "px");
                switch (elevator) {
                    case 1:
                        hasEnd1 = false;
                        hasDown1 = true;
                        layer1++;
                        $("#layer" + elevator).text(layer1);
                        hasComplete1 = true;
                        break;
                    case 2:
                        hasEnd2 = false;
                        hasDown2 = true;
                        layer2++;
                        $("#layer" + elevator).text(layer2);
                        hasComplete2 = true;
                        break;
                    case 3:
                        hasEnd3 = false;
                        hasDown3 = true;
                        layer3++;
                        $("#layer" + elevator).text(layer3);
                        hasComplete3 = true;
                        break;
                    case 4:
                        hasEnd4 = false;
                        hasDown4 = true;
                        layer4++;
                        $("#layer" + elevator).text(layer4);
                        hasComplete4 = true;
                        break;
                }
                clearInterval(moveInterval);
            }
        }, 10)
    }
}

//电梯人数增加
function btnPersonAddClick(event) {
    if ($('#getTarget' + event.attributes.value.value).val() != "" && $('#getWeight+event.attributes.value.value').val() != "") {
        if ($('#getTarget' + event.attributes.value.value).val() >= 0 && $('#getTarget' + event.attributes.value.value).val() <= 20 && $('#getTarget' + event.attributes.value.value).val() != parseInt(event.attributes.value.value)) {
            var obj = {};
            var sumWeigth = 0;
            obj.weight = $('#getWeight' + event.attributes.value.value).val().toString();
            obj.correct = $('#getTarget' + event.attributes.value.value).val().toString();
            obj.now = event.attributes.value.value.toString();
            if (obj.now < obj.correct) {
                obj.updown = "1";
            } else {
                obj.updown = "0";
            }
            personData.push(obj);
            sumWeigth = parseInt($('#personWeight' + event.attributes.value.value).text());
            sumWeigth += parseInt($('#getWeight' + event.attributes.value.value).val());
            $('#personWeight' + event.attributes.value.value).text(sumWeigth);
            $('#personInforBorderShow' + event.attributes.value.value).append("<tr id=\"personInfor" + $("#personInforBorderShow" + event.attributes.value.value + " tr").length + "\"><td>" + $('#getTarget' + event.attributes.value.value).val() + "</td>\n" +
                "                            <td>" + $('#getWeight' + event.attributes.value.value).val() + "</td>\n" +
                "                            <td><button type=\"button\" class=\"btn\" layer=\"" + event.attributes.value.value + "\" value=\"" + $("#personInforBorderShow" + event.attributes.value.value + " tr").length + "\" onclick=\"btnPersonDelClick(this)\">删除</button></td></tr>");
            $('#getTarget' + event.attributes.value.value).val("");
            $('#getWeight' + event.attributes.value.value).val("");
            var personNum = $('#personNumElv' + event.attributes.value.value).text();
            personNum = parseInt(personNum);
            personNum++;
            $('#personNumElv' + event.attributes.value.value).text(personNum);
            var topLength = event.attributes.value.value * 73 + 28;
            var leftLength = 670 + (personNum - 1) * 20;
            var showMans = "<div id=\"createMan" + event.attributes.value.value + personNum + "\" class=\"btnColumn\" style=\"left:" + leftLength + "px; top: " + topLength + "px; position: absolute; z-index:10;\">\n" +
                "        <div style=\"width:10px; height:10px; background: blue; border-radius: 10px 10px; margin-left:5px;\"></div>\n" +
                "        <div class=\"btnRow\">\n" +
                "            <div style=\"width:3px; height: 20px; background: blue;\"></div>\n" +
                "            <div style=\"width:10px; height: 15px; background: blue; margin-left: 1px;\"></div>\n" +
                "            <div style=\"width:3px; height: 20px; background: blue; margin-left: 1px;\"></div>\n" +
                "        </div>\n" +
                "        <div class=\"btnRow\" style=\"margin-top:0px;\">\n" +
                "            <div style=\"width:3px; height: 15px; background: blue; margin-left: 5px; margin-top:-5px;\"></div>\n" +
                "            <div style=\"width:3px; height: 15px; background: blue; margin-left:2px; margin-top:-5px;\"></div>\n" +
                "        </div>\n" +
                "    </div>";
            if (personNum < 12)
                $('#showMans' + event.attributes.value.value).append(showMans);
        } else {
            alert("输入层数不能大于20或小于0并且不能为当前层数");
        }//end if
    } else {
        alert("体重或目标层数不能为空！");
    }//end if
}

//电梯人数删除
function btnPersonDelClick(event) {
    for (var i = 0; i < personData.length; i++) {
        if (parseInt(personData[i].now) === parseInt(event.attributes.layer.value) && parseInt(personData[i].target) === parseInt($('#personInfor' + event.attributes.value.value + ' td:eq(0)').text())
            && parseInt(personData[i].weight) === parseInt($('#personInfor' + event.attributes.value.value + ' td:eq(1)').text())) {
            personData.splice(i, 1);
            break;
        }
    }
    var personNum = $('#personNumElv' + event.attributes.layer.value).text();
    personNum = parseInt(personNum);
    var sumWeight = $('#personWeight' + event.attributes.layer.value).text();
    sumWeight = parseInt(sumWeight);
    sumWeight -= parseInt($('#personInfor' + event.attributes.value.value + ' td:eq(1)').text());
    personNum--;
    if (personNum < 0)
        personNum = 0;
    $('#personNumElv' + event.attributes.layer.value).text(personNum);
    $('#personWeight' + event.attributes.layer.value).text(sumWeight);
    $('#personInfor' + event.attributes.value.value).remove();
    if ($("#createMan" + event.attributes.layer.value + event.attributes.value.value).length > 0)
        $("#createMan" + event.attributes.layer.value + event.attributes.value.value).remove();
}

//电梯按钮操作
function elevatorInfor(event) {
    if (event.attributes.state.value == 1) {
        if (hasComplete1 && hasComplete2 && hasComplete3 && hasComplete4 && personData != "") {
            layer1 = layer2 = layer3 = layer4 = 1;
            $("td[id^='state']").text("运行");
        } else {
            alert("电梯正在运行或者无人等待电梯")
        }
        if (layer1 === 1 && layer2 === 1 && layer3 === 1 && layer4 === 1) {
            getAnswer();
        }
    } else {
        if (hasComplete1 && hasComplete2 && hasComplete3 && hasComplete4) {
            alert("清除成功");
            personData = [];
            $("tr[id^='personInfor']").remove();
            $("text[id^='personNum']").text(0);
            $("text[id^='personWeight']").text(0);
            $("td[id^='state']").text("停止");
            $("div[id^='createMan']").remove();
        }
    }
}

//向后台发送请求
function getAnswer() {
    if (personData != "") {
        $.ajax({
            url: 'makeAnswer',
            method: 'post',
            data: {personData: JSON.stringify(personData)},
            success: function (res) {
                var obj = JSON.parse(res);
                //emmm返回数据把第一个电梯当成单层了，经过修改后和作业要求相对应
                elevator1Up = obj[4];
                elevator1Down = obj[5];
                elevator2Up = obj[0];
                elevator2Down = obj[1];
                elevator3Up = obj[2];
                elevator3Down = obj[3];
                elevator4Up = obj[6];
                elevator4Down = obj[7];
                var upMaxFloor1 = obj[8].upMaxFloor3;
                var upMinFloor1 = obj[8].upMinFloor3;
                var downMaxFloor1 = obj[8].downMaxFloor3;
                var downMinFloor1 = obj[8].downMinFloor3;
                var upMaxFloor2 = obj[8].upMaxFloor1;
                var upMinFloor2 = obj[8].upMinFloor1;
                var downMaxFloor2 = obj[8].downMaxFloor1;
                var downMinFloor2 = obj[8].downMinFloor1;
                var upMaxFloor3 = obj[8].upMaxFloor2;
                var upMinFloor3 = obj[8].upMinFloor2;
                var downMaxFloor3 = obj[8].downMaxFloor2;
                var downMinFloor3 = obj[8].downMinFloor2;
                var upMaxFloor4 = obj[8].upMaxFloor4;
                var upMinFloor4 = obj[8].upMinFloor4;
                var downMaxFloor4 = obj[8].downMaxFloor4;
                var downMinFloor4 = obj[8].downMinFloor4;
                if (upMinFloor1 != 100 && upMaxFloor1 != 0) {
                    if (upMinFloor1 == 0) {
                        elevatorMove(1, upMinFloor1, 0, 1);
                        var hasUpTime1 = setInterval(function () {
                            if (hasDown1 == true) {
                                elevatorMove(upMinFloor1, upMaxFloor1, 0, 1);
                                clearInterval(hasUpTime1);
                            }
                        }, 1000);
                    } else {
                        elevatorMove(1, upMaxFloor1, 0, 1);
                    }
                    var hasEndTime1 = setInterval(function () {
                        if (hasEnd1 == true) {
                            //console.log(hasEnd);
                            if (downMaxFloor1 != 0 && downMinFloor1 != 100) {
                                if (downMinFloor1 >= 1) {
                                    layer1 = downMaxFloor1;
                                    elevatorMove(downMaxFloor1, 1, 0, 1);
                                } else {
                                    layer1 = downMaxFloor1;
                                    elevatorMove(downMaxFloor1, 0, 0, 1);
                                }
                            }
                            if (upMaxFloor1 != 0 && downMinFloor1 == 100) {
                                layer1 = upMaxFloor1;
                                elevatorMove(upMaxFloor1, 1, 0, 1);
                            }
                            clearInterval(hasEndTime1);
                        }
                    }, 1000);
                }//end if
                if (upMinFloor2 != 100 && upMaxFloor2 != 0) {
                    if (upMinFloor2 == 0) {
                        elevatorMove(1, upMinFloor2, 0, 2);
                        var hasUpTime2 = setInterval(function () {
                            if (hasDown2 == true) {
                                elevatorMove(upMinFloor2, upMaxFloor2, 0, 2);
                                clearInterval(hasUpTime2);
                            }
                        }, 1000);
                    } else {
                        elevatorMove(1, upMaxFloor2, 0, 2);
                    }
                    var hasEndTime2 = setInterval(function () {
                        if (hasEnd2 == true) {
                            //console.log(hasEnd);
                            if (downMaxFloor2 != 0 && downMinFloor2 != 100) {
                                if (downMinFloor2 >= 1) {
                                    layer2 = downMaxFloor2;
                                    elevatorMove(downMaxFloor2, 1, 0, 2);
                                } else {
                                    layer2 = downMaxFloor2;
                                    elevatorMove(downMaxFloor2, 0, 0, 2);
                                }
                            }
                            if (upMaxFloor2 != 0 && downMinFloor2 == 100) {
                                layer2 = upMaxFloor2;
                                elevatorMove(upMaxFloor2, 1, 0, 2);
                            }
                            clearInterval(hasEndTime2);
                        }
                    }, 1000);
                }//end if
                if (upMinFloor3 != 100 && upMaxFloor3 != 0) {
                    if (upMinFloor3 == 0) {
                        elevatorMove(1, upMinFloor3, 0, 3);
                        var hasUpTime3 = setInterval(function () {
                            if (hasDown3 == true) {
                                elevatorMove(upMinFloor3, upMaxFloor3, 0, 3);
                                clearInterval(hasUpTime3);
                            }
                        }, 1000);
                    } else {
                        elevatorMove(1, upMaxFloor3, 0, 3);
                    }
                    var hasEndTime3 = setInterval(function () {
                        if (hasEnd3 == true) {
                            //console.log(hasEnd);
                            if (downMaxFloor3 != 0 && downMinFloor3 != 100) {
                                if (downMinFloor3 >= 1) {
                                    layer3 = downMaxFloor3;
                                    elevatorMove(downMaxFloor3, 1, 0, 3);
                                } else {
                                    layer3 = downMaxFloor3;
                                    elevatorMove(downMaxFloor3, 0, 0, 3);
                                }
                            }
                            if (upMaxFloor3 != 0 && downMinFloor3 == 100) {
                                layer3 = upMaxFloor3;
                                elevatorMove(upMaxFloor3, 1, 0, 3);
                            }
                            clearInterval(hasEndTime3);
                        }
                    }, 1000);
                }//end if
                if (upMinFloor4 != 100 && upMaxFloor4 != 0) {
                    if (upMinFloor4 == 0) {
                        elevatorMove(1, upMinFloor4, 0, 4);
                        var hasUpTime4 = setInterval(function () {
                            if (hasDown4 == true) {
                                elevatorMove(upMinFloor4, upMaxFloor4, 0, 4);
                                clearInterval(hasUpTime4);
                            }
                        }, 1000);
                    } else {
                        elevatorMove(1, upMaxFloor4, 0, 4);
                    }
                    var hasEndTime4 = setInterval(function () {
                        if (hasEnd4 == true) {
                            //console.log(hasEnd);
                            if (downMaxFloor4 != 0 && downMinFloor4 != 100) {
                                if (downMinFloor4 >= 1) {
                                    layer4 = downMaxFloor4;
                                    elevatorMove(downMaxFloor4, 1, 0, 4);
                                } else {
                                    layer4 = downMaxFloor4;
                                    elevatorMove(downMaxFloor4, 0, 0, 4);
                                }
                            }
                            if (upMaxFloor4 != 0 && downMinFloor4 == 100) {
                                layer4 = upMaxFloor4;
                                elevatorMove(upMaxFloor4, 1, 0, 4);
                            }
                            clearInterval(hasEndTime4);
                        }
                    }, 1000);
                }//end if
            }//end success
        })//end ajax
    }//end if
}