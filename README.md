# xing

玩转 AI，还有谁！

基于 Spring Boot + Spring AI Alibaba (DashScope) 的 LLM 应用示例工程，演示大模型对话能力的接入与用法。

## 技术栈

| 组件 | 版本 |
| --- | --- |
| Java | 21 |
| Spring Boot | 3.5.6 |
| Spring AI Alibaba (DashScope) | 1.1.0.0 |
| Maven | 3.9+ |

## 项目结构

```
xing/
├── pom.xml                        # 父工程（聚合模块）
└── ai-framework/
    └── springai/                  # Spring AI 示例模块
        └── src/main/java/com/xing/llm/
            ├── XingApplication.java
            └── controller/
                └── ChatModelController.java
```

## 快速开始

1. 在 `ai-framework/springai/src/main/resources/application.yml` 中配置 DashScope API Key：

```yaml
spring:
  ai:
    dashscope:
      api-key: <你的 API Key>
```

2. 编译并启动：

```bash
./mvnw clean install -DskipTests
./mvnw spring-boot:run -pl ai-framework/springai
```

## 接口说明

默认端口 `8080`，模型为通义千问 / DeepSeek 系列。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/model/call/string?message=` | 单轮字符串对话 |
| GET | `/model/call/messages?message=` | System + User 多消息对话 |
| GET | `/model/call/prompt?message=` | 自定义 Prompt 与 ChatOptions（示例指定 `deepseek-v3`） |
| GET | `/model/stream/string?message=` | SSE 流式输出 |

示例：

```bash
curl "http://localhost:8080/model/call/string?message=用一句话介绍Spring AI"
curl -N "http://localhost:8080/model/stream/string?message=写一首关于秋天的诗"
```

## 注意事项

- API Key 属于敏感信息，建议通过环境变量注入，避免提交到代码仓库。
- 流式接口返回 `text/event-stream`，需用支持 SSE 的客户端访问。
