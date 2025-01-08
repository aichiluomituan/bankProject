/**
 *  交易监控
 * @constructor
 */

// 全局变量存储当前页码和总页数
let currentPage = 1;
let totalPages = 1;


/**
 * 当主界面点击交易监控，发送请求 查询分页内容和页码
 * @constructor
 */
function ADISBTN(){
    axios({
        url: "http://localhost:8080/AdTransaction/selectATS",
        method: "post"
    }).then((result) => {
        // alert("1111")
        const tbody = document.getElementById('transactionTableBody');
        if (!tbody) {
            console.error('找不到表格元素');
            return;
        }

        // 正确获取数据列表
        const transactions = result.data.data.list;
        console.log('准备渲染的数据：', transactions);

        // 清空现有数据，但保留表头
        tbody.innerHTML = `
            <tr>
                <th>交易编号</th>
                <th>交易时间</th>
                <th>交易类型</th>
                <th>付款方</th>
                <th>收款方</th>
                <th>金额</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        `;

        // 检查数据是否为空
        if (!transactions || transactions.length === 0) {
            const tr = document.createElement('tr');
            tr.innerHTML = '<td colspan="8" style="text-align: center;">暂无交易数据</td>';
            tbody.appendChild(tr);
            //渲染页码
            document.getElementById("currentPageAD").textContent = 1; //当前页
            document.getElementById("totalPagesAD").textContent = 1; //总页数
            document.getElementById('jumpInput').max = 1;
            return;
        }

        // 渲染数据
        transactions.forEach(item => {
            const tr = document.createElement('tr');
            // 处理付款方和收款方的显示逻辑
            const payer = item.payer + (item.payerBankCard ? ` (${item.payerBankCard})` : '');
            const payee = item.payee + (item.payeeBankCard ? ` (${item.payeeBankCard})` : '');

            tr.innerHTML = `
                <td>${item.id || '--'}</td>
                <td>${item.date || '--'}</td>
                <td>${item.tradeType || '--'}</td>
                <td>${payer || '--'}</td>
                <td>${payee || '--'}</td>
                <td>¥${item.money || '0'}</td>
                <td class="StateTrade">${item.tradeState || '--'}</td>
                <td>
                    <button class="button" onclick="AuditTrade(${item.id})">审核</button>
                </td>
            `;
            tbody.appendChild(tr);
        });

        //渲染页码
        document.getElementById("currentPageAD").textContent = result.data.data.pageNo; //当前页
        document.getElementById("totalPagesAD").textContent = result.data.data.pageTotal; //总页数
        document.getElementById('jumpInput').max = result.data.data.pageTotal;


    }).catch((error) => {
        console.error('请求错误：', error);
        alert("查询异常，请联系管理员：" + error.message)
    })
}


// 更新页码信息
// function updatePageInfo(pageInfo) {
//     if (!pageInfo) return;
//
//     currentPage = pageInfo.pageNo;
//     totalPages = pageInfo.pageTotal;
//
//     // 更新显示
//     document.getElementById('currentPage').textContent = currentPage;
//     document.getElementById('totalPages').textContent = totalPages;
//
//     // 更新输入框最大值
//     document.getElementById('jumpInput').max = totalPages;
// }

// 跳转到首页
function goToFirstPage() {
    if (document.getElementById("currentPageAD").textContent==="1"){
        alert("当前已是首页！")
    }else {
        //更新页码信息
        document.getElementById("currentPageAD").textContent = 1
        document.getElementById("jumpInput").value = 1
        //调用条件查询
        conditionSelectAd();
    }
}

// 跳转到末页
function goToLastPage() {
   if (document.getElementById("currentPageAD").textContent===document.getElementById('jumpInput').max){
       alert("当前已是末页！")
   }else {
       //更新页码信息
       document.getElementById("currentPageAD").textContent = document.getElementById('jumpInput').max;
       document.getElementById("jumpInput").value = document.getElementById("jumpInput").max;
       //调用条件查询
       conditionSelectAd();
   }
}

// 跳转到指定页面
function jumpToPage() {
    const input = document.getElementById('jumpInput');
    const pageNum = parseInt(input.value);

    if (pageNum && pageNum >= 1 && pageNum <= document.getElementById("totalPagesAD").textContent) {
        //调用条件函数
        conditionSelectAd();
        // input.value = '';
    } else {
        alert('请输入有效的页码！');
    }
}

// // 页面切换函数
// function changePage(page) {
//     axios({
//         url: "http://localhost:8080/AdTransaction/list",
//         method: "post",
//         data: {
//             pageNo: page
//         }
//     }).then(response => {
//         if (response.data && response.data.code === 200) {
//             updatePageInfo(response.data.data);
//             // 这里添加更新表格数据的逻辑
//         }
//     }).catch(error => {
//         console.error('页面切换失败:', error);
//     });
// }

// 初始化加载第一页
// window.onload = function() {
//     changePage(1);
// };
//


/**
 *  审核功能,通过id查询到数据返回给前端，前端渲染到模糊框内
 * @constructor
 */
function AuditTrade(id){
    axios({
        url: "http://localhost:8080/AdTransaction/auditTrade",
        method: "post",
        data:{
            id : id
        }
    }).then((result) => {
        // 显示模态框并填充数据
        const payer = result.data.data.payer + (result.data.data.payerBankCard ? ` (${result.data.data.payerBankCard})` : '');
        const payee = result.data.data.payee + (result.data.data.payeeBankCard ? ` (${result.data.data.payeeBankCard})` : '');
        document.getElementById('aDId').value = result.data.data.id || '--';
        // alert(result.data.data.id)
        document.getElementById('aDTime').value = result.data.data.date || '--';
        document.getElementById('aDType').value = result.data.data.tradeType || '--';
        document.getElementById('aDPayee').value = payee || '--';
        document.getElementById('aDPayer').value = payer || '--';
        document.getElementById('aDMoney').value = result.data.data.money || '0';
        document.getElementById('aDState').value = result.data.data.tradeState || '--';
        document.getElementById('aDModal').style.display = 'block';
    });


    //用户点击提交发送请求 修改交易状态
    document.getElementById("updateButton").addEventListener('click',function(){
        // event.preventDefault()
        axios({
            url: "http://localhost:8080/AdTransaction/aDEdit",
            method: "post",
            data:{
                id : document.getElementById("aDId").value,
                tradeState: document.getElementById("aDState").value
            }
        }).then((result) => {
            //请求发送成功后关闭模糊框
            document.getElementById('aDModal').style.display = 'none';
            if (result.data.code==200){
                // alert("Aaaa")
                //这里因为前端问题不能直接刷新界面，再次调用conditionSelectAd()函数
                conditionSelectAd();
            }else {
                alert("修改失败")
            }
        })
    })
}

// 关闭模态框
document.querySelector('.close').addEventListener('click', function () {
    document.getElementById('aDModal').style.display = 'none';
});

document.querySelector('.cancel').addEventListener('click', function () {
    document.getElementById('aDModal').style.display = 'none';
});

// 点击模态框外区域关闭模态框
window.onclick = function (event) {
    const modal = document.getElementById('aDModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
};


let checkAdButton = document.getElementById("checkAdButton");
checkAdButton.addEventListener("click",conditionSelectAd)
//条件查询
function conditionSelectAd() {
    //获取起始日期和终止日期，最小金额和最大金额。进行逻辑判断
    let preTime = document.getElementById("preTime").value;
    let endTime = document.getElementById("endTime").value;
    let maxMoney = document.getElementById("maxMoney").value;
    let minMoney = document.getElementById("minMoney").value;
    //将日期转换成Date对象
    let start = new Date(preTime);
    let end = new Date(endTime);
    //将money转换成int类型
    if (minMoney===''&&maxMoney==''){
        minMoney = '0';
        maxMoney = '0';
    }
    let MinMoney = parseInt(minMoney);
    let MaxMoney = parseInt(maxMoney);
    console.log("s:"+start)
    console.log("ed:"+end)
    console.log("p:"+preTime)
    console.log("enT:"+endTime)
    console.log("跳转页为"+document.getElementById("jumpInput").value)
    console.log("selectLikeInput:"+document.getElementById("selectLikeInput").value)
    // if (maxPages)
    //如果用户输入的起始时间在终止日期之前并且最大金额大于最小金额 就允许发送请求
    if ((start <= end) && (MinMoney <= MaxMoney) || (preTime === '' && endTime === '' && MinMoney <= MaxMoney)){
        //符合逻辑条件，允许发送请求
        axios({
            url: "http://localhost:8080/AdTransaction/conditionSelect",
            method: "post",
            data:{
                tradeTypeAd : document.getElementById("tradeTypeAd").value,
                tradeStateAd : document.getElementById("tradeStateAd").value,
                preTime : preTime,
                endTime : endTime,
                minMoney : minMoney,
                maxMoney : maxMoney,
                selectName : document.getElementById("selectLikeInput").value,
                pageNo : document.getElementById("jumpInput").value
            }
        }).then((result) => {

            const tbody = document.getElementById('transactionTableBody');
            if (!tbody) {
                console.error('找不到表格元素');
                return;
            }

            // 清空现有数据，但保留表头
            tbody.innerHTML = `
            <tr>
                <th>交易编号</th>
                <th>交易时间</th>
                <th>交易类型</th>
                <th>付款方</th>
                <th>收款方</th>
                <th>金额</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        `;
            // 正确获取数据列表
            const transactions = result.data && result.data.data && result.data.data.list || null;
            console.log('准备渲染的数据：', transactions);

            // 检查数据是否为空
            if (transactions == null || transactions.length === 0) {
                const tr = document.createElement('tr');
                tr.innerHTML = '<td colspan="8" style="text-align: center;">暂无符合条件的交易数据</td>';
                tbody.appendChild(tr);
                //渲染页码
                document.getElementById("currentPageAD").textContent = 1; //当前页
                document.getElementById("totalPagesAD").textContent = 1; //总页数
                document.getElementById('jumpInput').max = 1;
                return;
            }

            // 渲染数据
            transactions.forEach(item => {
                const tr = document.createElement('tr');
                // 处理付款方和收款方的显示逻辑
                const payer = item.payer + (item.payerBankCard ? ` (${item.payerBankCard})` : '');
                const payee = item.payee + (item.payeeBankCard ? ` (${item.payeeBankCard})` : '');

                tr.innerHTML = `
                <td>${item.id || '--'}</td>
                <td>${item.date || '--'}</td>
                <td>${item.tradeType || '--'}</td>
                <td>${payer || '--'}</td>
                <td>${payee || '--'}</td>
                <td>¥${item.money || '0'}</td>
                <td class="StateTrade">${item.tradeState || '--'}</td>
                <td>
                    <button class="button" onclick="AuditTrade(${item.id})">审核</button>
                </td>
            `;
                tbody.appendChild(tr);
            });

            //渲染页面
            document.getElementById("currentPageAD").textContent = result.data.data.pageNo; //当前页
            document.getElementById("totalPagesAD").textContent = result.data.data.pageTotal; //总页数
            document.getElementById('jumpInput').max = result.data.data.pageTotal;


        })
    }else {
        alert("请输入符合逻辑的条件")
    }
}

/**
 *  模糊查询--搜索交易记录(通过收款方或者付款方姓名)
 */
document.getElementById("selectLikeBtn").addEventListener("click",conditionSelectAd);

function FuzzyQueriesAd(){
    //获取用户输入的值
    axios({
        url: "http://localhost:8080/AdTransaction/selectLikeData",
        method: "post",
        data:{
            selectName : document.getElementById("selectLikeInput").value,
            pageNo : document.getElementById("jumpInput").value
        }
    }).then((result) => {
        const tbody = document.getElementById('transactionTableBody');
        if (!tbody) {
            console.error('找不到表格元素');
            return;
        }


        // 清空现有数据，但保留表头
        tbody.innerHTML = `
            <tr>
                <th>交易编号</th>
                <th>交易时间</th>
                <th>交易类型</th>
                <th>付款方</th>
                <th>收款方</th>
                <th>金额</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        `;
        // 正确获取数据列表
        const transactions = result.data && result.data.data && result.data.data.list || null;
        console.log('准备渲染的数据：', transactions);

        // 检查数据是否为空
        if (transactions == null || transactions.length === 0) {
            const tr = document.createElement('tr');
            tr.innerHTML = '<td colspan="8" style="text-align: center;">暂无符合条件的交易数据</td>';
            tbody.appendChild(tr);
            //渲染页码
            document.getElementById("currentPageAD").textContent = 1; //当前页
            document.getElementById("totalPagesAD").textContent = 1; //总页数
            document.getElementById('jumpInput').max = 1;
            return;
        }

        // 渲染数据
        transactions.forEach(item => {
            const tr = document.createElement('tr');
            // 处理付款方和收款方的显示逻辑
            const payer = item.payer + (item.payerBankCard ? ` (${item.payerBankCard})` : '');
            const payee = item.payee + (item.payeeBankCard ? ` (${item.payeeBankCard})` : '');

            tr.innerHTML = `
                <td>${item.id || '--'}</td>
                <td>${item.date || '--'}</td>
                <td>${item.tradeType || '--'}</td>
                <td>${payer || '--'}</td>
                <td>${payee || '--'}</td>
                <td>¥${item.money || '0'}</td>
                <td class="StateTrade">${item.tradeState || '--'}</td>
                <td>
                    <button class="button" onclick="AuditTrade(${item.id})">审核</button>
                </td>
            `;
            tbody.appendChild(tr);
        });

        //渲染页面
        document.getElementById("currentPageAD").textContent = result.data.data.pageNo; //当前页
        document.getElementById("totalPagesAD").textContent = result.data.data.pageTotal; //总页数
        document.getElementById('jumpInput').max = result.data.data.pageTotal;
    }).catch((error) => {
        console.error('请求错误：', error);
        alert("查询异常，请联系管理员：" + error.message)
    })
}













