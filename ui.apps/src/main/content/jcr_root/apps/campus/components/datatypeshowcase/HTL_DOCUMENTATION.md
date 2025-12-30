# HTL (Sightly) Documentation - Datatype Showcase Component

This document explains all the HTL tags and concepts used in the `datatypeshowcase` component to render data **without Sling Models**.

---

## Table of Contents
1. [Built-in Objects](#1-built-in-objects)
2. [Expression Syntax](#2-expression-syntax)
3. [Block Statements](#3-block-statements)
4. [Context Options](#4-context-options)
5. [Examples from the Component](#5-examples-from-the-component)

---

## 1. Built-in Objects

HTL provides several built-in objects that are available without any imports:

| Object | Description |
|--------|-------------|
| `properties` | Direct access to JCR properties of the current resource |
| `resource` | The current Sling Resource object |
| `wcmmode` | Access to WCM mode (edit, preview, disabled) |
| `currentPage` | The current AEM page |
| `request` | The Sling request object |

### Using `properties` (No Sling Model needed!)

```html
<!-- Access any JCR property directly -->
${properties.textField}
${properties.numberField}
${properties.dateField}
```

The `properties` object gives you direct access to all properties stored on the component's JCR node.

---

## 2. Expression Syntax

### Basic Expression
```html
${expression}
```

### Expression with Options
```html
${expression @ option='value'}
```

### Multiple Options
```html
${expression @ option1='value1', option2='value2'}
```

---

## 3. Block Statements

### 3.1 `data-sly-test` - Conditional Rendering

Renders the element only if the condition is **truthy**.

```html
<!-- Element is only rendered if textField has a value -->
<div data-sly-test="${properties.textField}">
    Text: ${properties.textField}
</div>
```

**With negation:**
```html
<!-- Renders only if checkboxField is false/empty -->
<sly data-sly-test="${!properties.checkboxField}">Disabled</sly>
```

**Storing result in a variable:**
```html
<sly data-sly-test.hasText="${properties.textField}">
    <!-- hasText variable is now available -->
</sly>
```

---

### 3.2 `data-sly-repeat` - Iterating Arrays

Used to iterate over arrays/lists and repeat the **current element**.

```html
<ul>
    <li data-sly-repeat="${properties.multiSelectField}">${item}</li>
</ul>
```

**With custom variable name:**
```html
<li data-sly-repeat.fruit="${properties.multiSelectField}">${fruit}</li>
```

**Accessing list metadata:**
```html
<li data-sly-repeat="${properties.items}">
    Index: ${itemList.index}      <!-- 0-based index -->
    Count: ${itemList.count}      <!-- 1-based count -->
    First: ${itemList.first}      <!-- true if first item -->
    Last: ${itemList.last}        <!-- true if last item -->
    Middle: ${itemList.middle}    <!-- true if not first or last -->
    Odd: ${itemList.odd}          <!-- true if odd index -->
    Even: ${itemList.even}        <!-- true if even index -->
</li>
```

---

### 3.3 `data-sly-list` - Iterating with More Control

Similar to `data-sly-repeat` but **doesn't repeat** the current element.

```html
<div data-sly-list.child="${resource.children}">
    <!-- Each child is processed, but div is NOT repeated -->
    <p>${child.name}</p>
</div>
```

---

### 3.4 `data-sly-use` - Using External Objects

Imports Java classes, Sling Models, or other HTL templates.

```html
<!-- Using a template file -->
<sly data-sly-use.templates="core/wcm/components/commons/v1/templates.html"/>

<!-- Using a Sling Model (NOT used in this component - we avoid it!) -->
<sly data-sly-use.model="com.example.MyModel"/>
```

---

### 3.5 `data-sly-call` - Calling Templates

Calls a template defined elsewhere.

```html
<sly data-sly-call="${templates.placeholder @ isEmpty=!properties.textField}"/>
```

---

### 3.6 `data-sly-resource` - Including Resources

Includes another resource/component.

```html
<sly data-sly-resource="${'childNode' @ resourceType='my/component'}"/>
```

---

### 3.7 `<sly>` Element

A special HTL element that **doesn't render** in the final HTML. Used as a wrapper for HTL logic.

```html
<!-- The <sly> tag disappears in output, only content remains -->
<sly data-sly-test="${properties.showMessage}">
    <p>This message is visible</p>
</sly>
```

---

## 4. Context Options

The `@ context` option controls how values are escaped/rendered for security.

| Context | Usage |
|---------|-------|
| `text` (default) | HTML-escaped text |
| `html` | Renders HTML markup |
| `attribute` | Safe for HTML attributes |
| `uri` | For URLs |
| `styleToken` | For CSS property values |
| `scriptToken` | For JavaScript values |
| `unsafe` | No escaping (dangerous!) |

### Examples:

```html
<!-- Default text context (HTML escaped) -->
${properties.textField}

<!-- Render HTML content -->
${properties.richText @ context='html'}

<!-- For style attributes -->
style="color: ${properties.color @ context='styleToken'};"

<!-- For URLs with extension -->
<a href="${properties.path @ extension='html'}">Link</a>
```

---

## 5. Examples from the Component

### Rendering Simple Text
```html
<span>${properties.textField}</span>
```

### Rendering Rich Text (HTML)
```html
<div>${properties.richText @ context='html'}</div>
```

### Conditional Rendering
```html
<div data-sly-test="${properties.dateField}">
    <span>Date:</span>
    <span>${properties.dateField}</span>
</div>
```

### Boolean Fields (Checkbox/Switch)
```html
<sly data-sly-test="${properties.checkboxField}">Enabled</sly>
<sly data-sly-test="${!properties.checkboxField}">Disabled</sly>
```

### Multi-Value Fields (Arrays)
```html
<ul>
    <li data-sly-repeat="${properties.multiSelectField}">${item}</li>
</ul>
```

### Path with Link
```html
<a href="${properties.pathField @ extension='html'}">${properties.pathField}</a>
```

### Color with Style
```html
<span style="background-color: ${properties.colorField @ context='styleToken'};"></span>
```

### Email Link
```html
<a href="mailto:${properties.email}">${properties.email}</a>
```

### External URL
```html
<a href="${properties.externalUrl}" target="_blank" rel="noopener noreferrer">
    ${properties.externalUrl}
</a>
```

### Iterating Child Resources (Composite Multifield)
```html
<div data-sly-list.child="${resource.children}">
    <sly data-sly-test="${child.name == 'compositeMultifield'}">
        <div data-sly-list.item="${child.children}">
            <p>Title: ${item.valueMap.title}</p>
            <p>Description: ${item.valueMap.description}</p>
        </div>
    </sly>
</div>
```

### Debug: Show All Properties (Edit Mode Only)
```html
<details data-sly-test="${wcmmode.edit || wcmmode.preview}">
    <summary>Debug</summary>
    <table>
        <tr data-sly-repeat="${properties}">
            <td>${itemList.key}</td>
            <td>${item}</td>
        </tr>
    </table>
</details>
```

---

## Quick Reference Card

| Task | HTL Code |
|------|----------|
| Display text | `${properties.field}` |
| Display HTML | `${properties.field @ context='html'}` |
| Conditional | `data-sly-test="${condition}"` |
| Loop array | `data-sly-repeat="${array}"` |
| Loop with name | `data-sly-repeat.item="${array}"` |
| Invisible wrapper | `<sly>...</sly>` |
| Check edit mode | `${wcmmode.edit}` |
| Add .html extension | `${path @ extension='html'}` |
| CSS value | `${value @ context='styleToken'}` |
| Iterate properties | `data-sly-repeat="${properties}"` |
| Access property key | `${itemList.key}` |

---

## Resources

- [HTL Specification](https://github.com/adobe/htl-spec)
- [AEM HTL Documentation](https://experienceleague.adobe.com/docs/experience-manager-htl/content/getting-started.html)
