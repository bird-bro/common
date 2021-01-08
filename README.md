# dispose
Eliminate more than 90% of try catch code blocks, use an elegant Assert method to verify business exceptions, 
focus only on business logic, and not spend a lot of effort on writing redundant try catch code blocks.

### BusinessException
Follow Alibaba 《Java Development Manual》(Songshan Edition).Error code enum：BusinessErrorCode
### Result
Universal return。
Json：
{
  "timestamp": "2021-01-06 03:07:10.484",
  "status": 200,
  "message": "OK",
  "code": "00000",
  "info": "一切OK",
  "data": null
}

### employ
Annotate the Application：  @EnableGlobalDispose





